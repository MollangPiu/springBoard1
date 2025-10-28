<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<meta charset="EUC-KR">
<head>
  <meta charset="UTF-8" />
  <title>ȸ������</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <style>
    :root{
      --bg: linear-gradient(165deg, #f6f9ff, #eef2ff 35%, #f7fff4);
      --surface:#ffffff; --text:#0f172a; --muted:#64748b; --line:#e5e7eb;
      --brand1:#6366f1; --brand2:#22c55e; --brand: linear-gradient(135deg, var(--brand1), var(--brand2));
      --ring: 0 0 0 4px rgba(99,102,241,.18);
      --shadow: 0 10px 30px rgba(2, 6, 23, .08);
      --rxl:18px; --rmd:12px;
      --font: system-ui,-apple-system,"Segoe UI",Roboto,"Noto Sans KR",Arial,sans-serif;
    }
    @media (prefers-color-scheme: dark){
      :root{ --bg: radial-gradient(1200px 800px at 10% -10%, #0b1220, transparent 50%), #0a0f1e;
        --surface:#0b1220; --text:#e5e7eb; --muted:#94a3b8; --line:#1f2937; --ring:0 0 0 4px rgba(99,102,241,.28);
        --shadow:0 14px 34px rgba(0,0,0,.45); }
    }
    *{box-sizing:border-box}
    html,body{height:100%}
    body{margin:0;background:var(--bg);color:var(--text);font-family:var(--font)}
    a{color:#4f46e5;text-decoration:none} a:hover{text-decoration:underline}
    .page{min-height:100%;display:grid;place-items:center;padding:28px}
    .card{width:100%;max-width:520px;background:var(--surface);border:1px solid var(--line);
      border-radius:var(--rxl);box-shadow:var(--shadow);overflow:hidden}
    .head{padding:26px 28px 8px;display:flex;gap:12px;align-items:center;border-bottom:1px solid rgba(0,0,0,.03)}
    .logo{width:40px;height:40px;border-radius:11px;background:var(--brand)}
    .title{margin:0;font-size:22px;letter-spacing:-.2px}
    .form{padding:22px 28px 28px;display:grid;gap:16px}
    .label{font-size:13px;color:var(--muted);margin-bottom:6px;display:inline-block}
    .group{display:grid;gap:6px}
    .input{display:flex;align-items:center;gap:10px;border:1px solid var(--line);border-radius:var(--rmd);
      background:#fff;padding:12px 14px;transition:box-shadow .15s,border-color .15s,background .15s}
    .input input{width:100%;border:0;outline:0;background:transparent;font:inherit;color:inherit}
    .input svg{flex:0 0 18px;opacity:.55}
    .input:focus-within{border-color:var(--brand1);box-shadow:var(--ring)}
    .btn{border:0;border-radius:var(--rmd);padding:12px 16px;cursor:pointer;font-weight:600}
    .btn-primary{background:var(--brand);color:#fff;width:100%;transition:transform .06s,box-shadow .15s,filter .15s}
    .btn-primary:hover{filter:brightness(1.02);box-shadow:0 10px 26px rgba(34,197,94,.25)}
    .btn-primary:active{transform:translateY(1px)}
    .muted{text-align:center;color:var(--muted);font-size:14px}
    .badge{display:inline-flex;align-items:center;gap:8px;padding:8px 12px;border-radius:999px;
      border:1px dashed var(--line);color:var(--muted);font-size:12px;background:rgba(99,102,241,.05)}
    .badge code{font-family:ui-monospace,SFMono-Regular,Menlo,Consolas,"Liberation Mono",monospace}
    @media (max-width:520px){.head{padding:22px 22px 6px}.form{padding:18px 22px 24px}}
  </style>
</head>
<body>
  <main class="page">
    <section class="card" role="region" aria-labelledby="title">
      <header class="head">
        <span class="logo" aria-hidden="true"></span>
        <h1 id="title" class="title">ȸ������</h1>
      </header>

      <form class="form" method="post" action="${pageContext.request.contextPath}/member/registerProcess" novalidate>
        <!-- idx(pk)�� DB �ڵ� ���� �� ���� ���� X -->

        <div class="group">
          <label class="label" for="reg_user_id">���̵�</label>
          <div class="input">
            <svg viewBox="0 0 24 24" width="18" height="18" aria-hidden="true">
              <path fill="currentColor" d="M12 12a5 5 0 1 0-5-5 5 5 0 0 0 5 5Zm0 2c-4.33 0-8 2.17-8 5v1h16v-1c0-2.83-3.67-5-8-5Z"/>
            </svg>
            <input type="text" id="reg_user_id" name="userId" required minlength="4" maxlength="50" placeholder="����/���� 4�� �̻�" />
          </div>
          <small class="muted">�ߺ� �˻�� �������� ó���ϼ���.</small>
        </div>

        <div class="group">
          <label class="label" for="reg_user_pw">��й�ȣ</label>
          <div class="input">
            <svg viewBox="0 0 24 24" width="18" height="18" aria-hidden="true">
              <path fill="currentColor" d="M17 8h-1V6a4 4 0 1 0-8 0v2H7a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2v-8a2 2 0 0 0-2-2ZM9 6a3 3 0 1 1 6 0v2H9Zm8 12H7v-8h10Z"/>
            </svg>
            <input type="password" id="reg_user_pw" name="userPw" required minlength="6" maxlength="255" placeholder="����/����/��ȣ 6�� �̻�" />
          </div>
        </div>

        <div class="group">
          <label class="label" for="reg_user_pw2">��й�ȣ Ȯ��</label>
          <div class="input">
            <svg viewBox="0 0 24 24" width="18" height="18" aria-hidden="true">
              <path fill="currentColor" d="M17 8h-1V6a4 4 0 1 0-8 0v2H7a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2v-8a2 2 0 0 0-2-2ZM9 6a3 3 0 1 1 6 0v2H9Zm8 12H7v-8h10Z"/>
            </svg>
            <input type="password" id="reg_user_pw2" name="userPw2" required minlength="6" maxlength="255" placeholder="��й�ȣ ���Է�" />
          </div>
        </div>

        <div class="group">
          <label class="label" for="nickname">�г���</label>
          <div class="input">
            <svg viewBox="0 0 24 24" width="18" height="18" aria-hidden="true">
              <path fill="currentColor" d="M12 12.75a5.75 5.75 0 1 0-5.75-5.75A5.75 5.75 0 0 0 12 12.75Zm0 2.5c-4.97 0-9 2.49-9 5.56V23h18v-2.19c0-3.07-4.03-5.56-9-5.56Z"/>
            </svg>
            <input type="text" id="nickname" name="nickName" required maxlength="50" placeholder="ǥ�õ� �̸�" />
          </div>
        </div>

        <button class="btn btn-primary" type="submit">�����ϱ�</button>
        <p class="muted">�̹� ������ �ֳ���? <a href="${pageContext.request.contextPath}/member/login">�α���</a></p>

        <!-- ���� �޽��� �ڸ�
        <p style="color:#ef4444;font-size:12px;margin:0">�̹� ��� ���� ���̵��Դϴ�.</p>
        -->
      </form>

      <div class="form" style="padding-top:0">
        <span class="badge">���� ��: <code>POST /auth/register</code> ��������Ʈ</span>
      </div>
    </section>
  </main>
</body>
</html>
