package kh.semi.thduo.alarm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kh.semi.thduo.alarm.model.service.AlarmService;
import kh.semi.thduo.alarm.model.vo.AlarmVo;
import kh.semi.thduo.member.vo.MemberVo;

/**
 * Servlet implementation class SendAlarmListAjaxController
 */
@WebServlet("/sendAlarmList.ax")
public class SendAlarmListAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendAlarmListAjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("보낸알람 컨트롤러 dopost");
		
		// ajax 에 데이터 보내기 위한 객체 생성
		PrintWriter out = response.getWriter();
		// 세션에 담긴 정보 가져오기
		MemberVo vo = (MemberVo)request.getSession().getAttribute("ssMV");
		// 사용할 변수 선언
		String mNickname = "";
		
		// 로그인이 안되어있을때
		if(vo == null) {
			response.sendRedirect("login");
		} else { // 되어있을때
			mNickname = vo.getmNickname();
			System.out.println("보낸 알람 서비스 호출 mNickname:" + mNickname);
			// 세션에 담긴 정보 받아 서비스 호출
			ArrayList<AlarmVo> result = new AlarmService().sendListAlarm(mNickname);
			System.out.println("리스트 결과:" + result);
			// 받아온 결과를 gson에 담아 ajax에 넘김
			Gson gobj = new GsonBuilder().setPrettyPrinting().create();
			String resStr = gobj.toJson(result);
			out.println(resStr);
			out.flush();
			out.close();
		}
	}

}
