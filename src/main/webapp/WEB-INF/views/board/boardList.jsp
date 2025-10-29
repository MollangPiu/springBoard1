<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.soft.study.dto.board.BoardListDTO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>게시판 목록</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <style>
    /* ===== Design Tokens ===== */
    :root{
      --bg: linear-gradient(165deg, #f6f9ff, #eef2ff 35%, #f7fff4);
      --surface:#ffffff; --text:#0f172a; --muted:#64748b; --line:#e5e7eb;
      --brand1:#6366f1; --brand2:#22c55e; --brand: linear-gradient(135deg, var(--brand1), var(--brand2));
      --ring: 0 0 0 4px rgba(99,102,241,.18);
      --shadow: 0 10px 30px rgba(2,6,23,.08);
      --rxl:18px; --rmd:12px; --rs:10px;
      --font: system-ui,-apple-system,"Segoe UI",Roboto,"Noto Sans KR",Arial,sans-serif;
      --code: ui-monospace,SFMono-Regular,Menlo,Consolas,"Liberation Mono",monospace;
      --dog:#fef3c7; --cat:#dbeafe; --bird:#dcfce7; --rabbit:#fae8ff; --etc:#fde2e2;
    }
    @media (prefers-color-scheme: dark){
      :root{
        --bg: radial-gradient(1200px 800px at 10% -10%, #0b1220, transparent 50%), #0a0f1e;
        --surface:#0b1220; --text:#e5e7eb; --muted:#94a3b8; --line:#1f2937;
        --ring: 0 0 0 4px rgba(99,102,241,.28);
        --shadow: 0 14px 34px rgba(0,0,0,.45);
        --dog:#3a2f00; --cat:#0b1b3a; --bird:#0c2a16; --rabbit:#2a0b3a; --etc:#3a0b0b;
      }
    }

    /* ===== Base ===== */
    *{box-sizing:border-box}
    html,body{height:100%}
    body{margin:0;background:var(--bg);color:var(--text);font-family:var(--font)}
    a{color:#4f46e5;text-decoration:none}
    a:hover{text-decoration:underline}
    .sr-only{position:absolute;left:-9999px;top:auto;width:1px;height:1px;overflow:hidden}
    small{color:var(--muted)}
    .page{min-height:100%;display:grid;place-items:start center;padding:28px}

    /* ===== Card ===== */
    .card{
      width:100%;max-width:980px;background:var(--surface);border:1px solid var(--line);
      border-radius:var(--rxl);box-shadow:var(--shadow);overflow:hidden
    }
    .head{
      padding:22px 24px;display:flex;gap:14px;align-items:center;justify-content:space-between;
      border-bottom:1px solid rgba(0,0,0,.04)
    }
    .brand{display:flex;gap:12px;align-items:center}
    .logo{width:40px;height:40px;border-radius:11px;background:var(--brand)}
    .title{margin:0;font-size:22px;letter-spacing:-.2px}
    .sub{margin:2px 0 0;color:var(--muted);font-size:13px}

    /* ===== Toolbar / Filters ===== */
    .toolbar{
      padding:14px 16px;display:flex;gap:10px;align-items:center;justify-content:space-between;flex-wrap:wrap;
      border-bottom:1px solid var(--line)
    }
    .filters{display:flex;gap:10px;flex-wrap:wrap}
    .input, .select{
      display:flex;align-items:center;gap:10px;background:#fff;border:1px solid var(--line);border-radius:var(--rmd);
      padding:10px 12px;transition:box-shadow .15s,border-color .15s
    }
    .input input{border:0;outline:0;background:transparent;font:inherit;color:inherit;width:200px}
    .select select{border:0;outline:0;background:transparent;font:inherit;color:inherit;appearance:none;padding-right:18px}
    .select{position:relative}
    .select:after{content:"▾";position:absolute;right:10px;top:50%;transform:translateY(-50%);font-size:12px;color:#6b7280;pointer-events:none}
    .input:focus-within,.select:focus-within{border-color:var(--brand1);box-shadow:var(--ring)}
    .btn{border:0;border-radius:var(--rmd);padding:10px 14px;font-weight:600;cursor:pointer}
    .btn-primary{background:var(--brand);color:#fff}
    .btn-ghost{background:transparent;border:1px dashed var(--line);color:var(--muted)}
    .right{display:flex;gap:8px;align-items:center}

    /* ===== Table ===== */
    .table-wrap{width:100%;overflow:auto}
    table{width:100%;border-collapse:separate;border-spacing:0}
    thead th{
      text-align:left;padding:12px 14px;font-size:13px;color:var(--muted);background:#f8fafc;border-bottom:1px solid var(--line)
    }
    tbody td{
      padding:14px;border-bottom:1px solid var(--line);vertical-align:middle;
      max-width:0; /* ellipsis 위한 기반 */
    }
    tbody tr:hover{background:#fafafa}
    .num,.views,.date{white-space:nowrap;color:var(--muted);font-variant-numeric:tabular-nums}
    .title-cell{
      display:flex;gap:8px;align-items:center;min-width:200px
    }
    .title-link{
      display:inline-block;max-width:100%;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;
      font-weight:600;color:inherit
    }
    .author{color:var(--muted)}
    .chip{
      display:inline-flex;align-items:center;gap:6px;border-radius:999px;padding:6px 10px;font-size:12px;border:1px solid var(--line)
    }
    .chip.dog{background:var(--dog)} .chip.cat{background:var(--cat)} .chip.bird{background:var(--bird)}
    .chip.rabbit{background:var(--rabbit)} .chip.etc{background:var(--etc)}
    .badge{
      display:inline-flex;align-items:center;gap:6px;border-radius:999px;padding:6px 10px;font-size:12px;
      background:rgba(99,102,241,.08);color:#3730a3;border:1px dashed rgba(99,102,241,.25)
    }

    /* ===== Pagination ===== */
    .pagination{
      display:flex;gap:6px;justify-content:center;align-items:center;padding:16px;border-top:1px solid var(--line);flex-wrap:wrap
    }
    .page-btn{
      min-width:36px;height:36px;display:grid;place-items:center;border:1px solid var(--line);border-radius:10px;
      background:#fff;color:inherit;text-decoration:none;font-weight:600;padding:0 10px
    }
    .page-btn.active{background:#eef2ff;border-color:#c7d2fe}
    .page-btn:hover{background:#f8fafc}

    /* ===== Empty ===== */
    .empty{
      padding:40px; text-align:center; color:var(--muted);
    }

    @media (max-width:820px){
      .num,.views{display:none}
    }
  </style>
</head>
<body>

<%
	List<BoardListDTO> lists = (List<BoardListDTO>)request.getAttribute("lists");
%>
  <main class="page">
    <section class="card" role="region" aria-labelledby="title">
      <header class="head">
        <div class="brand">
          <span class="logo" aria-hidden="true"></span>
          <div>
            <h1 id="title" class="title">게시판</h1>
            <p class="sub">목록에서 글을 선택하거나, 새 글을 작성하세요.</p>
          </div>
        </div>
        <a class="btn btn-ghost" href="${pageContext.request.contextPath}/member/login">로그인</a>
      </header>

      <!-- Toolbar -->
      <div class="toolbar">
        <form class="filters" method="get" action="${pageContext.request.contextPath}/board/list">
          <div class="select">
            <label class="sr-only" for="animal">동물 필터</label>
            <select id="animal" name="searchAnimal">
              <option value="">전체 동물</option>
              <option value="DOG">강아지</option>
              <option value="CAT">고양이</option>
              <option value="BIRD">새</option>
              <option value="RABBIT">토끼</option>
              <option value="ETC">기타</option>
            </select>
          </div>

          <div class="select">
            <label class="sr-only" for="sort">정렬</label>
            <select id="sort" name="sort">
              <option value="recent">최신순</option>
              <option value="popular">조회수순</option>
              <option value="title">제목순</option>
            </select>
          </div>

          <div class="input">
            <label class="sr-only" for="q">검색어</label>
            <input id="q" name="searchKeyword" type="text" placeholder="제목 검색" />
          </div>

          <button class="btn btn-primary" type="submit">검색</button>
        </form>

        <div class="right">
          <a class="btn btn-primary" href="${pageContext.request.contextPath}/board/register">글쓰기</a>
        </div>
      </div>

      <!-- Table -->
      <div class="table-wrap">
        <table aria-describedby="table-caption">
          <caption id="table-caption" class="sr-only">게시글 목록</caption>
          <thead>
            <tr>
              <th class="num" style="width:80px">번호</th>
              <th>제목</th>
              <th style="width:160px">작성자</th>
              <th style="width:140px">좋아하는 동물</th>
              <th class="date" style="width:150px">작성일</th>
              <th class="views" style="width:100px">조회수</th>
            </tr>
          </thead>
          <tbody>
          <%
          	for(int i=0; i < lists.size(); i++) {
          		%>
          			<tr onClick="detail(<%=lists.get(i).getBoardIdx()%>)">
          				<td class="num"><%=lists.get(i).getBoardIdx() %></td>
          				<td>
			                <div class="title-cell">
			                  <a class="title-link"><%=lists.get(i).getBoardTitle()%></a>
			                  <!-- <span class="badge">NEW</span> -->
			                </div>
		                </td>
		                <td><span class="author"><%=lists.get(i).getBoardNickName() %></span></td>
		                <td>
		                	<%if(lists.get(i).getBoardFavoriteAnimal().equals("DOG")) {
		                		%><span class="chip dog"><%
		                	  }
		                	  else if(lists.get(i).getBoardFavoriteAnimal().equals("CAT")) {
		                		%><span class="chip cat"><%
		                	  }
		                	  else if(lists.get(i).getBoardFavoriteAnimal().equals("BIRD")) {
		                		%><span class="chip bird"><%
		                	  }
		                	  else if(lists.get(i).getBoardFavoriteAnimal().equals("RABBIT")) {
		                		%><span class="chip rabbit"><%
		                	  }
		                	  else if(lists.get(i).getBoardFavoriteAnimal().equals("ETC")) {
		                		%><span class="chip etc"><%
		                	  }
		                	%>
		                		<%=lists.get(i).getAnimalKor() %>
		                	</span>
	                	</td>
		                <td class="date"><%=lists.get(i).getBoardCreatedAt() %></td>
		                <td class="views"><%=lists.get(i).getBoardViewCount() %></td>
		              </tr>
          		<%
          	}
          %>
            
          </tbody>
        </table>
      </div>

      <!-- Empty State (데이터 없을 때 이 블록만 남기고 table tbody 비우기)
      <div class="empty">
        아직 게시글이 없습니다. <a href="/board/write">첫 글을 작성</a>해 보세요!
      </div>
      -->

      <!-- Pagination -->
      <nav class="pagination" aria-label="페이지네이션">
        <a class="page-btn" href="/board/list?page=1" aria-label="처음">&laquo;</a>
        <a class="page-btn" href="/board/list?page=1" aria-label="이전">&lsaquo;</a>
        <a class="page-btn active" href="/board/list?page=1">1</a>
        <a class="page-btn" href="/board/list?page=2">2</a>
        <a class="page-btn" href="/board/list?page=3">3</a>
        <a class="page-btn" href="/board/list?page=2" aria-label="다음">&rsaquo;</a>
        <a class="page-btn" href="/board/list?page=10" aria-label="끝">&raquo;</a>
      </nav>
    </section>
  </main>
  
<script>
	function detail(idx) {
		location.href="${pageContext.request.contextPath}/board/detail?idx="+idx;
	}
</script>
</body>
</html>
