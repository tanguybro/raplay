<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html lang="fr">
<%@ include file="layout.jsp" %>

<body class="text-center">
  <form data-aos="fade-zoom-in" data-aos-easing="ease" data-aos-duration="3000" class="form-signin" method="post">
        <img class="mb-4" src="./resources/images/logo_1.1.png" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Connexion</h1>
        <label for="inputEmail" class="sr-only">Email</label>
        <input type="email" name="inputEmail" class="form-control" placeholder="Email" required autofocus>
        <label for="inputPassword" class="sr-only">Mot de passe</label>
        <input type="password" name="inputPassword" class="form-control" placeholder="Mot de passe" required>
        <div class="checkbox mb-3">
          <label>
            <input type="checkbox" value="remember-me"> Se souvenir de moi
          </label>
        </div>
        <button class="btn btn-success btn1" type="submit">Se connecter</button>
        <p class="mt-5 mb-3 text-muted">${errorMessage}</p>
      </form>
</body>

<script>
  AOS.init({
    duration: 1000,
  });

  $(document).ready(function(){
    $('body').scrollspy({target: ".navbar", offset: 50});
  });

</script>

</html>
