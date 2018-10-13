/**
 * Copyright (C),2015-2018
 * FileNmae: InpatientDaoImpl
 * Author:   Administrator
 * Date:     2018/8/3117:25
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.InpatientDao;
import com.youma.util.ConnectionDB;
import com.youma.util.Page;
import com.youma.vo.Inpatient;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈泽群
 */
public class InpatientDaoImpl extends BaseDao implements InpatientDao {
    @Override
    public int inpatientAdd(Inpatient inpatient) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO his.inpatient\n" +
                "(medicalNum,\n" +
                "nurse,\n" +
                "bedNum,\n" +
                "deposit,\n" +
                "illness)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?);";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, inpatient.getMedicalNum());
            ps.setString(2, inpatient.getNurse());
            ps.setString(3, inpatient.getBedNum());
            ps.setDouble(4, inpatient.getDeposit());
            ps.setString(5, inpatient.getIllness());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int updateInpatient(Inpatient inpatient) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE his.inpatient\n" +
                "SET\n" +
                "medicalNum = ?,\n" +
                "nurse = ?,\n" +
                "bedNum = ?,\n" +
                "deposit = ?,\n" +
                "illness = ?\n" +
                "WHERE medicalNum = ?;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, inpatient.getMedicalNum());
            ps.setString(2, inpatient.getNurse());
            ps.setString(3, inpatient.getBedNum());
            ps.setDouble(4, inpatient.getDeposit());
            ps.setString(5, inpatient.getIllness());
            ps.setInt(6, inpatient.getMedicalNum());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int delInpatient(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "update inpatient left join hossettle on inpatient.medicalNum=hossettle.medicalNum set inpatient.flag=4\n" +
                "WHERE inpatient.medicalNum=? and hossettle.flag=1 ";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public List<Inpatient> findAllInpatient() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT inpatient.medicalNum,\n" +
                "    inpatient.nurse,\n" +
                "    inpatient.bedNum,\n" +
                "    inpatient.deposit,\n" +
                "    inpatient.illness,inpatient.flag,inptime,registerName,register.phoneNum as 'a',doctorName,depName\n" +
                "FROM his.inpatient join register on inpatient.medicalNum=register.medicalNum\n" +
                "join doctor on doctorID=ID join department on doctor.depID=department.ID";
        List<Inpatient> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inpatient inpatient = new Inpatient();
                inpatient.setMedicalNum(rs.getInt("medicalNum"));
                inpatient.setNurse(rs.getString("nurse"));
                inpatient.setBedNum(rs.getString("bedNum"));
                inpatient.setDeposit(rs.getDouble("deposit"));
                inpatient.setIllness(rs.getString("illness"));
                inpatient.setFlag(rs.getInt("flag"));
                inpatient.setInpTime(sdf.format(rs.getTimestamp("inptime")));
                inpatient.setName(rs.getString("registerName"));
                inpatient.setPhone(rs.getString("a"));
                inpatient.setDoctor(rs.getString("doctorName"));
                inpatient.setDepName(rs.getString("depName"));
                list.add(inpatient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public Inpatient findInpatient(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT inpatient.medicalNum,\n" +
                "    inpatient.nurse,\n" +
                "    inpatient.bedNum,\n" +
                "    inpatient.deposit,\n" +
                "    inpatient.illness\n" +
                "FROM his.inpatient\n" +
                "WHERE medicalNum=?";
        Inpatient inpatient = new Inpatient();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                inpatient.setMedicalNum(rs.getInt("medicalNum"));
                inpatient.setNurse(rs.getString("nurse"));
                inpatient.setBedNum(rs.getString("bedNum"));
                inpatient.setDeposit(rs.getDouble("deposit"));
                inpatient.setIllness(rs.getString("illness"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return inpatient;
    }

    @Override
    public int inpCount(Inpatient inpatient) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT count(inpatient.medicalNum)\n" +
                "FROM his.inpatient join register on inpatient.medicalNum=register.medicalNum\n" +
                "join doctor on doctorID=ID join department on doctor.depID=department.ID where 1=1 \n";
        if (inpatient.getMedicalNum() != 0) {
            sql += " and inpatient.medicalNum=? ";
        }
        if (inpatient.getDoctor() != null && !inpatient.getDoctor().isEmpty()) {
            sql += " and doctor.doctorName like \"%\" ? \"%\" ";
        }
        if (inpatient.getDepName() != null && !inpatient.getDepName().isEmpty()) {
            sql += " and department.depName like \"%\" ? \"%\" ";
        }
        if (StringUtils.isNotBlank(inpatient.getTime1()) || StringUtils.isNotBlank(inpatient.getTime2())) {
            sql += " and inpatient.inptime between ? and ?";
        }
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 0;
            if (inpatient.getMedicalNum() != 0) {
                ps.setInt(++index, inpatient.getMedicalNum());
            }
            if (inpatient.getDoctor() != null && !inpatient.getDoctor().isEmpty()) {
                ps.setString(++index, inpatient.getDoctor());
            }
            if (inpatient.getDepName() != null && !inpatient.getDepName().isEmpty()) {
                ps.setString(++index, inpatient.getDepName());
            }

            if (StringUtils.isNotBlank(inpatient.getTime1()) || StringUtils.isNotBlank(inpatient.getTime2())) {
                if (StringUtils.isNotBlank(inpatient.getTime1())) {
                    ps.setTimestamp(++index, new Timestamp(sdf1.parse(inpatient.getTime1()).getTime()));
                } else {
                    ps.setTimestamp(++index, new Timestamp(sdf1.parse("1980-01-01").getTime()));
                }
                if (StringUtils.isNotBlank(inpatient.getTime2())) {
                    long k = 24 * 60 * 60 * 1000 - 1;
                    ps.setTimestamp(++index, new Timestamp(sdf1.parse(inpatient.getTime2()).getTime() + k));
                } else {
                    ps.setTimestamp(++index, new Timestamp(System.currentTimeMillis()));
                }
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                col = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public List<Inpatient> findInp(Inpatient inpatient1, Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT inpatient.medicalNum,\n" +
                "    inpatient.nurse,\n" +
                "    inpatient.bedNum,\n" +
                "    inpatient.deposit,\n" +
                "    inpatient.illness,inpatient.flag,inptime,registerName,register.phoneNum as a,doctorName,depName\n" +
                "FROM his.inpatient join register on inpatient.medicalNum=register.medicalNum\n" +
                "join doctor on doctorID=ID join department on doctor.depID=department.ID  where  1=1 ";
        if (inpatient1.getMedicalNum() != 0) {
            sql += " and inpatient.medicalNum=? ";
        }
        if (inpatient1.getDoctor() != null && !inpatient1.getDoctor().isEmpty()) {
            sql += " and doctor.doctorName like \"%\" ? \"%\" ";
        }
        if (inpatient1.getDepName() != null && !inpatient1.getDepName().isEmpty()) {
            sql += " and department.depName like \"%\" ? \"%\" ";
        }
        if (StringUtils.isNotBlank(inpatient1.getTime1()) || StringUtils.isNotBlank(inpatient1.getTime2())) {
            sql += " and inpatient.inptime between ? and ?";
        }
        sql += " order by inpatient.medicalNum desc limit ?,?";
        List<Inpatient> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            int index = 0;
            if (inpatient1.getMedicalNum() != 0) {
                ps.setInt(++index, inpatient1.getMedicalNum());
            }
            if (inpatient1.getDoctor() != null && !inpatient1.getDoctor().isEmpty()) {
                ps.setString(++index, inpatient1.getDoctor());
            }
            if (inpatient1.getDepName() != null && !inpatient1.getDepName().isEmpty()) {
                ps.setString(++index, inpatient1.getDepName());
            }
            if (StringUtils.isNotBlank(inpatient1.getTime1()) || StringUtils.isNotBlank(inpatient1.getTime2())) {
                if (StringUtils.isNotBlank(inpatient1.getTime1())) {
                    ps.setTimestamp(++index, new Timestamp(sdf1.parse(inpatient1.getTime1()).getTime()));
                } else {
                    ps.setTimestamp(++index, new Timestamp(sdf1.parse("1980-01-01").getTime()));
                }
                if (StringUtils.isNotBlank(inpatient1.getTime2())) {
                    long k = 24 * 60 * 60 * 1000 - 1;
                    ps.setTimestamp(++index, new Timestamp(sdf1.parse(inpatient1.getTime2()).getTime() + k));
                } else {
                    ps.setTimestamp(++index, new Timestamp(System.currentTimeMillis()));
                }
            }

            ps.setInt(++index, page.getOffset());
            ps.setInt(++index, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                Inpatient inpatient = new Inpatient();
                inpatient.setMedicalNum(rs.getInt("medicalNum"));
                inpatient.setNurse(rs.getString("nurse"));
                inpatient.setBedNum(rs.getString("bedNum"));
                inpatient.setDeposit(rs.getDouble("deposit"));
                inpatient.setIllness(rs.getString("illness"));
                inpatient.setFlag(rs.getInt("flag"));
                inpatient.setInpTime(sdf.format(rs.getTimestamp("inptime")));
                inpatient.setName(rs.getString("registerName"));
                inpatient.setPhone(rs.getString("a"));
                inpatient.setDoctor(rs.getString("doctorName"));
                inpatient.setDepName(rs.getString("depName"));
                list.add(inpatient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }
}
