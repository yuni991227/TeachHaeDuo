package kh.semi.thduo.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.thduo.member.vo.MemberVo;
import kh.semi.thduo.pencil.model.service.PencilService;
import kh.semi.thduo.pencil.model.vo.PencilVo;
import kh.semi.thduo.teacher.model.service.TeacherService;
import kh.semi.thduo.teacher.model.vo.TeacherVo;

/**
 * Servlet implementation class MypageController
 */
@WebServlet("/mypage")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("마이페이지 doget");
		MemberVo ssMV = (MemberVo)request.getSession().getAttribute("ssMV");
		String mId = null;;
		String roleSt = null;
		if(ssMV == null) {
			response.sendRedirect("login");
		} else {
			mId = ssMV.getmId();
			roleSt = ssMV.getRoleSt();
			System.out.println(roleSt);
			
			if(roleSt == null) {
				response.sendRedirect("login");
			} else if(roleSt.equals("T")) {
				PencilVo vo = new PencilVo();
				vo.setmId(mId);
				int result = new PencilService().checkPencil(mId);
				request.setAttribute("balance", result);
				System.out.println("선생 마이페이지 진입");
				request.getRequestDispatcher("WEB-INF/view/mypage/mypageTeacher.jsp").forward(request, response);
			} else if(roleSt.equals("S")) {
				PencilVo vo = new PencilVo();
				vo.setmId(mId);
				int result = new PencilService().checkPencil(mId);
				request.setAttribute("balance", result);
				System.out.println("학생 마이페이지 진입");
				request.getRequestDispatcher("WEB-INF/view/mypage/mypageStudent.jsp").forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
