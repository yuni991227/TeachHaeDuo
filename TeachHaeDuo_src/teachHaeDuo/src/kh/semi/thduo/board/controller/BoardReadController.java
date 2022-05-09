package kh.semi.thduo.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.thduo.board.service.BoardService;
import kh.semi.thduo.board.vo.BoardReCommentVo;
import kh.semi.thduo.board.vo.BoardVo;

/**
 * Servlet implementation class BoardReadController
 */
@WebServlet("/BoardRead")
public class BoardReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bNo = request.getParameter("bno");
		
		BoardVo result = new BoardService().boardRead(bNo);
		ArrayList<BoardReCommentVo> rvo = new BoardService().boardReCommentRead(bNo);
		System.out.println(result);
		result.setbContent(result.getbContent().replaceAll("(\r\n|\n)", "<br>"));
		result.setbContent(result.getbContent().replaceAll(" ", "&nbsp;"));
		System.out.println(result);
		System.out.println(rvo);
		request.setAttribute("rvo", rvo);
		request.setAttribute("bvo", result);
		request.getRequestDispatcher("BoardRead.jsp").forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
