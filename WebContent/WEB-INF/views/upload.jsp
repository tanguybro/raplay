<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html lang="fr">
<%@ include file="layout.jsp" %>

<body>

  <div id="background">

    <nav class="tabs bg-dark">
      <a class="logo" href="index.html"><img src="./images/logo_1.0.png" height="30" width="30"> Raplay</a>
      <a class="nav-link" href="#">Parcourir</a>
      <a class="nav-link" href="#">Uploader</a>
      <input type="search" placeholder="Rechercher..." aria-describedby="button-addon1" class="input-design">
    </nav>

    <div class="container-fluid center-block bloc">
      <div class="row">
        <div class="col-md-12">
          <h2 style="margin:2%;">Glisser ou sélectionner des fichiers à uploader</h2>

          <input type="file" class="w-25">

          <div class="radioBox form-check-inline">
            <div class="form-check">
              <input type="radio" class="form-check-input" id="materialChecked" name="publicRadio" checked>
              <label class="form-check-label">Public</label>
            </div>

            <div class="form-check">
              <input type="radio" class="form-check-input" id="materialChecked" name="privateRadio">
              <label class="form-check-label">Privé</label>
            </div>

          </div>

        </div>
      </div>

      <div class="container-fluid center-block bloc">
        <div class="row">
          <div class="col-md-12">
            <button type="button" class="btn btn-success btn1" name="upload" disabled>Upload</button>
          </div>
        </div>
      </div>


    </div>

  </div>

<div class="player">
  <div class="container-fluid">
    <div class="row h-100">
      <div class="col-md-3 command">

        <input type="image" src="./resources/images/previous.png" height="50px" width="50px" name="button"/>
        <input type="image" src="./resources/images/play.png" height="50px" width="50px" name="button"/>
        <input type="image" src="./resources/images/next.png" height="50px" width="50px" name="button"/>
        <input type="image" src="./resources/images/shuffle.png" height="50px" width="50px" name="button"/>
        <input type="image" src="./resources/images/repeat.png" height="50px" width="50px" name="button"/>
      </div>

      <div class="col-md-2 info">
        <div class="image">
          <img src="./resources/images/pochette.jpg" alt="" height="45px" width="45px">
        </div>
        <div class="desc">
          <p>Son</p>
          <p style="color:#515151;">Artiste</p>
        </div>
      </div>

      <div class="col-md-7 bar my-auto">
        <div class="progress" style="height:5px; width:85%;">
          <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%;border-radius:10px;"/>
        </div>

      </div>

    </div>
  </div>

</div>


</body>

</html>
