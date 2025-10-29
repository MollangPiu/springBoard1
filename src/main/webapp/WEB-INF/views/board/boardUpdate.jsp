<%@page import="kr.soft.study.dto.board.BoardUpdateDetailDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>게시글 수정</title>
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
    }
    @media (prefers-color-scheme: dark){
      :root{
        --bg: radial-gradient(1200px 800px at 10% -10%, #0b1220, transparent 50%), #0a0f1e;
        --surface:#0b1220; --text:#e5e7eb; --muted:#94a3b8; --line:#1f2937;
        --ring: 0 0 0 4px rgba(99,102,241,.28);
        --shadow: 0 14px 34px rgba(0,0,0,.45);
      }
    }
    *{box-sizing:border-box}
    html,body{height:100%}
    body{margin:0;background:var(--bg);color:var(--text);font-family:var(--font)}
    a{color:#4f46e5;text-decoration:none}
    a:hover{text-decoration:underline}
    .sr-only{position:absolute;left:-9999px;top:auto;width:1px;height:1px;overflow:hidden}
    .page{min-height:100%;display:grid;place-items:start center;padding:28px}

    .card{width:100%;max-width:780px;background:var(--surface);border:1px solid var(--line);
      border-radius:var(--rxl);box-shadow:var(--shadow);overflow:hidden}
    .head{padding:22px 24px;display:flex;gap:14px;align-items:center;justify-content:space-between;border-bottom:1px solid rgba(0,0,0,.04)}
    .brand{display:flex;gap:12px;align-items:center}
    .logo{width:40px;height:40px;border-radius:11px;background:var(--brand)}
    .title{margin:0;font-size:22px;letter-spacing:-.2px}
    .sub{margin:2px 0 0;color:var(--muted);font-size:13px}

    .form{padding:22px 24px 26px;display:grid;gap:18px}
    .field{display:grid;gap:8px}
    .label{font-size:13px;color:var(--muted)}
    .row{display:flex;gap:14px;flex-wrap:wrap}

    .input,.select{display:flex;align-items:center;gap:10px;background:#fff;border:1px solid var(--line);border-radius:var(--rmd);padding:12px 14px;transition:box-shadow .15s,border-color .15s}
    .input input{border:0;outline:0;background:transparent;font:inherit;color:inherit;width:100%}
    .select{position:relative}
    .select select{border:0;outline:0;background:transparent;font:inherit;color:inherit;appearance:none;padding-right:18px}
    .select:after{content:"▾";position:absolute;right:12px;top:50%;transform:translateY(-50%);font-size:12px;color:#6b7280;pointer-events:none}
    .input:focus-within,.select:focus-within{border-color:var(--brand1);box-shadow:var(--ring)}

    .textarea{border:1px solid var(--line);border-radius:12px;background:#fff;padding:12px 14px;min-height:220px;resize:vertical;line-height:1.6;font:inherit;color:inherit}
    .textarea:focus{outline:0;border-color:var(--brand1);box-shadow:var(--ring)}

    .actions{display:flex;justify-content:space-between;align-items:center;gap:10px;flex-wrap:wrap;padding:16px 24px;border-top:1px solid var(--line)}
    .btn{border:0;border-radius:12px;padding:10px 14px;font-weight:600;cursor:pointer}
    .btn-primary{background:var(--brand);color:#fff}
    .btn-ghost{background:transparent;border:1px dashed var(--line);color:var(--muted)}
    .btn-danger{background:#ef4444;color:#fff}

    @media (max-width:560px){
      .row .field{flex:1 1 100%}
    }
  </style>
</head>
<body>

<%
	BoardUpdateDetailDTO detail = (BoardUpdateDetailDTO)request.getAttribute("detail");

%>
  <main class="page">
    <section class="card" role="region" aria-labelledby="title">
      <header class="head">
        <div class="brand">
          <span class="logo" aria-hidden="true"></span>
          <div>
            <h1 id="title" class="title">게시글 수정</h1>
            <p class="sub">제목, 내용, 좋아하는 동물을 수정한 뒤 저장하세요.</p>
          </div>
        </div>
        <a class="btn btn-ghost" href="${pageContext.request.contextPath}/board/list">목록으로</a>
      </header>

      <!-- 수정 폼 -->
      <form class="form" method="post" action="${pageContext.request.contextPath}/board/updateProcess" accept-charset="UTF-8">
        <!-- 글 번호는 서버에서 채워주세요 -->
        <input type="hidden" name="boardIdx" value="${detail.boardIdx}" />

        <div class="row">
          <div class="field" style="flex:1 1 420px">
            <label class="label" for="title">제목</label>
            <div class="input">
              <!-- value는 서버에서 기존 제목으로 채워 넣기 -->
              <input id="title" name="title" type="text" required maxlength="200" placeholder="제목을 입력하세요" value="${detail.boardTitle}" />
            </div>
          </div>

          <div class="field" style="width:220px;min-width:220px">
            <label class="label" for="favorite_animal">좋아하는 동물</label>
            <div class="select">
              <!-- 서버에서 기존 코드에 맞춰 selected 설정 -->
              <select id="favorite_animal" name="favorite_animal" required>
				  <option value="DOG"    ${detail.boardFavoriteAnimal == 'DOG'    ? 'selected' : ''}>강아지</option>
				  <option value="CAT"    ${detail.boardFavoriteAnimal == 'CAT'    ? 'selected' : ''}>고양이</option>
				  <option value="BIRD"   ${detail.boardFavoriteAnimal == 'BIRD'   ? 'selected' : ''}>새</option>
				  <option value="RABBIT" ${detail.boardFavoriteAnimal == 'RABBIT' ? 'selected' : ''}>토끼</option>
				  <option value="ETC"    ${detail.boardFavoriteAnimal == 'ETC'    ? 'selected' : ''}>기타</option>
			  </select>
            </div>
          </div>
        </div>

        <div class="field">
          <label class="label" for="content">내용</label>
          <!-- 본문은 텍스트로만, 서버에서 기존 내용 채워 넣기 -->
          <textarea id="content" name="content" class="textarea" required>${detail.boardContent}</textarea>
        </div>

        <div class="actions">
          <a class="btn btn-ghost" href="/board/view?idx=12">취소</a>
          <div>
            <a class="btn btn-danger" href="/board/delete?idx=12">삭제</a>
            <button class="btn btn-primary" type="submit">저장</button>
          </div>
        </div>

        <!-- 강의 팁 -->
        <!-- 서버 검증 권장: title(4~200), content(6+), favorite_animal(DOG/CAT/BIRD/RABBIT/ETC) -->
      </form>
    </section>
  </main>
</body>
</html>