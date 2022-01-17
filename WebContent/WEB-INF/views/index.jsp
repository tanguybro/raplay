<!doctype html>

<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html lang="fr">
<%@ include file="layout.jsp" %>

<body>

  <div class="background">

	<div class="container-fluid entete">
	  <div class="row">
		<div data-aos="fade-zoom-in" data-aos-easing="ease" data-aos-duration="3000" class="col-7 title my-auto">
			<a href="index.jsp"><img src="resources/images/logo_1.0.png" height="60" width="60"> Raplay</a>
		</div>

		<div data-aos="fade-zoom-in" data-aos-easing="ease" data-aos-duration="3000" class="col-5 param my-auto">
			<a href="#">Créer un compte</a>
			<a href="login">Connexion</a>
		</div>
	  </div>
	</div>

    <div class="container-fluid contentAccueil">

      <div class="row">
        <div class="col-md-5 desc my-auto">
          <h2 data-aos="fade-right" data-aos-easing="ease" data-aos-delay="800">Lorem ipsum mihi nomen rap est Lorem ipsum mihi nomen rap est</h2>
          <h2 data-aos="fade-right" data-aos-easing="ease" data-aos-delay="900">Lorem ipsum mihi nomen rap est</h2>
          <h2 data-aos="fade-right" data-aos-easing="ease" data-aos-delay="1000">Decouvrez le meilleur du rap</h2>
        </div>

        <div class="col-md-7 center-block">
          <a href="#"><img data-aos="zoom-in" data-aos-easing="ease" data-aos-delay="800" class="img images-container1" src="resources/images/pochette.jpg" height="175" width="175"></a>
          <a href="#"><img data-aos="zoom-in" data-aos-easing="ease" data-aos-delay="300" class="img images-container2" src="resources/images/pochette.jpg" height="250" width="250"></a>
          <a href="#"><img data-aos="zoom-in" data-aos-easing="ease" data-aos-delay="800" class="img images-container3" src="resources/images/pochette.jpg" height="175" width="175"></a>
        </div>
      </div>

      <div class="row" style="margin-top: 7%; margin-bottom: 3%;">
        <div class="col-md-12 center-block">
          <button type="button" class="btn btn-success btn1" name="button" onclick="window.location.href = 'upload';">Upload</button>
          <button type="button" class="btn btn-success btn1" name="button">Lecteur web</button>
        </div>
      </div>
    </div>




  </div>

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
