<%@page import="kr.soft.study.dto.board.BoardDetailDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>게시글 상세보기</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <style>
    :root{
      --bg: linear-gradient(165deg, #f6f9ff, #eef2ff 35%, #f7fff4);
      --surface:#ffffff; --text:#0f172a; --muted:#64748b; --line:#e5e7eb;
      --brand1:#6366f1; --brand2:#22c55e; --brand: linear-gradient(135deg, var(--brand1), var(--brand2));
      --ring: 0 0 0 4px rgba(99,102,241,.18);
      --shadow: 0 10px 30px rgba(2,6,23,.08);
      --rxl:18px; --rmd:12px;
      --font: system-ui,-apple-system,"Segoe UI",Roboto,"Noto Sans KR",Arial,sans-serif;
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
    *{box-sizing:border-box}
    html,body{height:100%}
    body{margin:0;background:var(--bg);color:var(--text);font-family:var(--font)}
    a{color:#4f46e5;text-decoration:none}
    a:hover{text-decoration:underline}
    .sr-only{position:absolute;left:-9999px;top:auto;width:1px;height:1px;overflow:hidden}
    .page{min-height:100%;display:grid;place-items:start center;padding:28px}

    .card{width:100%;max-width:980px;background:var(--surface);border:1px solid var(--line);
      border-radius:var(--rxl);box-shadow:var(--shadow);overflow:hidden}
    .head{padding:22px 24px;display:flex;gap:14px;align-items:center;justify-content:space-between;border-bottom:1px solid rgba(0,0,0,.04)}
    .brand{display:flex;gap:12px;align-items:center}
    .logo{width:40px;height:40px;border-radius:11px;background:var(--brand)}
    .title{margin:0;font-size:22px;letter-spacing:-.2px}
    .sub{margin:2px 0 0;color:var(--muted);font-size:13px}

    .meta{padding:14px 24px;display:flex;gap:10px;align-items:center;flex-wrap:wrap;border-bottom:1px solid var(--line);background:#f8fafc}
    .chip{display:inline-flex;align-items:center;gap:6px;border-radius:999px;padding:6px 10px;font-size:12px;border:1px solid var(--line);background:#fff}
    .chip.dog{background:var(--dog)} .chip.cat{background:var(--cat)} .chip.bird{background:var(--bird)} .chip.rabbit{background:var(--rabbit)} .chip.etc{background:var(--etc)}
    .muted{color:var(--muted)}
    .dot::before{content:"•";margin:0 8px;color:var(--muted)}

    .body{padding:24px;line-height:1.8;font-size:16px}
    .body h2{font-size:20px;margin:18px 0 8px}
    .body p{margin:0 0 14px}
    .body pre,.body code{font-family:ui-monospace,SFMono-Regular,Menlo,Consolas,"Liberation Mono",monospace;background:#f3f4f6;border:1px solid var(--line);border-radius:10px}
    .body pre{padding:14px;overflow:auto}

    .actions{padding:16px 24px;display:flex;justify-content:space-between;align-items:center;border-top:1px solid var(--line);flex-wrap:wrap;gap:10px}
    .btn{border:0;border-radius:12px;padding:10px 14px;font-weight:600;cursor:pointer}
    .btn-primary{background:var(--brand);color:#fff}
    .btn-ghost{background:transparent;border:1px dashed var(--line);color:var(--muted)}
    .btn-danger{background:#ef4444;color:#fff}
  </style>
</head>
<body>
<%
	BoardDetailDTO detail = (BoardDetailDTO)request.getAttribute("detail");
%>
  <main class="page">
    <section class="card" role="region" aria-labelledby="title">
      <header class="head">
        <div class="brand">
          <span class="logo" aria-hidden="true"></span>
          <div>
            <h1 id="title" class="title">${detail.getBoardTitle()}</h1>
            <p class="sub">글을 읽고 목록으로 돌아가거나 수정/삭제할 수 있어요.</p>
          </div>
        </div>
        <a class="btn btn-ghost" href="${pageContext.request.contextPath}/board/list">목록으로</a>
      </header>

      <!-- 메타 정보 -->
      <div class="meta" aria-label="게시글 메타 정보">
        <!-- 좋아하는 동물 chip: 클래스(cat/dog/bird/rabbit/etc)와 텍스트만 바꾸세요 -->
        <%if(detail.getBoardFavoriteAnimal().equals("DOG")) {
        	%><span class="chip dog" title="좋아하는 동물"><%
         }
          else if(detail.getBoardFavoriteAnimal().equals("CAT")) {
        	%><span class="chip cat" title="좋아하는 동물"><%
          }
          else if(detail.getBoardFavoriteAnimal().equals("BIRD")) {
        	%><span class="chip bird" title="좋아하는 동물"><%
          }
          else if(detail.getBoardFavoriteAnimal().equals("RABBIT")) {
        	%><span class="chip rabbit" title="좋아하는 동물"><%
          }
          else if(detail.getBoardFavoriteAnimal().equals("ETC")) {
        	%><span class="chip etc" title="좋아하는 동물"><%
          }
        	%>
        	${detail.getAnimalKor()}</span>
        <span class="muted">작성자</span><strong>${detail.getBoardNickName()}</strong>
        <span class="muted dot"></span>
        <span class="muted">작성일</span><time datetime="2025-10-29">${detail.getBoardCreatedAt()}</time>
        <span class="muted dot"></span>
        <span class="muted">조회수</span><strong>${detail.getBoardViewCount()}</strong>
      </div>

      <!-- 본문 -->
      <article class="body">
        ${detail.getBoardContent()}
      </article>

      <!-- 액션 -->
      <div class="actions">
        <div>
          <a class="btn btn-ghost" href="${pageContext.request.contextPath}/board/list">← 목록</a>
        </div>
        <div>
          <a class="btn btn-primary" href="/board/edit?idx=12">수정</a>
          <a class="btn btn-danger"  href="/board/delete?idx=12">삭제</a>
        </div>
      </div>
    </section>
  </main>
</body>
</html>
