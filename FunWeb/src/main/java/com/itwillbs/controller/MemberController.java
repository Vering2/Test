package com.itwillbs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;

public class MemberController extends HttpServlet{
	
	RequestDispatcher dispatcher =null;
	MemberService memberService = null;
	
// HttpServlet 처리담당자 -> 자동으로 doGet, doPost 호출
	// -> 재정의 해서 사용
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController doGet()");
		doProcess(request, response);
	}//doGet()

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController doPost()");
		doProcess(request, response);
	}//doPost()
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController doProcess()");
		//가상주소 뽑아오기
		String sPath=request.getServletPath();
		System.out.println("뽑은 가상주소 :  " + sPath);
		//뽑은 가상주소 비교하기 => 실제페이지 연결
		if(sPath.equals("/insert.me")) {
			// member/join.jsp 주소변경 없이 연결
			dispatcher 
		    = request.getRequestDispatcher("member/join.jsp");
		dispatcher.forward(request, response);
		}//
		if(sPath.equals("/insertPro.me")) {
			System.out.println("뽑은 가상주소 비교 : /insertPro.me");
			//회원가입 => MemberService => MemberDAO
			// MemberService 객체생성
			memberService = new MemberService();
			// insertMember() 메서드 호출
			memberService.insertMember(request);
			//로그인 이동 => 주소변경하면서 이동
			response.sendRedirect("login.me");
		}//
		if(sPath.equals("/login.me")) {
			// member/login.jsp 주소변경 없이 이동
			dispatcher 
		    = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request, response);
		}//
		
		if(sPath.equals("/loginPro.me")) {
			System.out.println("뽑은 가상주소 비교 : /insertPro.me");
	// MemberService 객체생성
			memberService = new MemberService();
	// MemberDTO memberDTO = userCheck(request) 메서드 호출
	MemberDTO memberDTO = memberService.userCheck(request);
	// memberDTO != null 아이디 비밀번호 일치=> 세션값 저장=>main.me
	// memberDTO == null 아이디 비밀번호 틀림=> member/msg.jsp
		}
		
		
	}//doProcess()

}//클래스
