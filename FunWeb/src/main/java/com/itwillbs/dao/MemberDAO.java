package com.itwillbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.domain.MemberDTO;

public class MemberDAO {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs =null;
	
	//1,2 단계 디비 연결 메서드  정의 => 필요로 할때 호출 사용
	public Connection getConnection() throws Exception {
		Context init = new InitialContext();
		DataSource ds=
		(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		return con;
	}
	//기억장소 해제 메서드()
	public void dbClose() {
		//  => con, pstmt, rs 기억장소 해제
		if(rs != null) {try {rs.close();} catch (SQLException e) {	}}			
		if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {	}}
		if(con != null) {try {con.close();} catch (SQLException e) {	}}
	}
	
	
	// 자바빈(JavaBean) MemberBean , MemberDTO , MemberVO 
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO insertMember()");
		try {
			//1,2단계 디비연결
			con = getConnection();
			//3 sql 구문 insert
			String sql="insert into members(id,pass,name,date) values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPass());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setTimestamp(4, memberDTO.getDate());
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}//insertMember()
	
	public MemberDTO userCheck(MemberDTO memberDTO2) {
		MemberDTO memberDTO = null;
		try {
			//1,2 디비연결
			//3 sql select * from members where id=? and pass=?
			//4 실행 결과 => rs 저장
			//5 rs 첫번째 행으로 데이터 있으면 
			//  memberDTO 객체생성, set메서드 호출 rs열 데이터 저장
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return memberDTO;
	}//userCheck()

	
}//class
