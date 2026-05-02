<%
    Object usuario = session.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%@ page import="java.util.List" %>
<%@ page import="aa_property.dao.IncidenciaDAO" %>
<%@ page import="aa_property.model.Incidencia" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>AA Property Services | Panel de clientes</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
   <header class="site-header">
  <div class="container nav-container">

    <div class="nav-left">
      <a href="index.html" class="logo">
        <img src="assets/img/AAP-S.svg" alt="Logo AA Property Services">
      </a>
      <a href="index.html" class="brand">AA Property Services</a>
    </div>

    <button class="menu-toggle" aria-label="Abrir menú" aria-expanded="false">
      <span></span>
      <span></span>
      <span></span>
    </button>

    <nav class="main-nav">
      <ul>
        <li><a href="index.html" class="active">Inicio</a></li>
        <li><a href="servicios.html">Servicios</a></li>
        <li><a href="sobre-nosotros.html">Sobre nosotros</a></li>
        <li><a href="contacto.html">Contacto</a></li>
      </ul>
    </nav>

    <div class="nav-buttons">
      <a href="login-clientes.html" class="btn btn-outline">Acceso clientes</a>
      <a href="login-personal.html" class="btn btn-primary">Acceso personal</a>
    </div>

  </div>
</header>

<%
    aa_property.model.Propietario p = (aa_property.model.Propietario) session.getAttribute("usuario");
%>

<h2>Bienvenido, <%= p.getEmail() %></h2>
<%
    IncidenciaDAO dao = new IncidenciaDAO();
    List<Incidencia> incidencias = dao.listarPorVilla(p.getIdVilla());
%>
<h3>Mis incidencias</h3>

<%
    if (incidencias.isEmpty()) {
%>
    <p>No tienes incidencias registradas.</p>
<%
    } else {
        for (Incidencia i : incidencias) {
%>
    <div style="border:1px solid #ccc; padding:10px; margin:10px 0;">
        <p><strong>ID:</strong> <%= i.getIdIncidencia() %></p>
        <p><strong>Título:</strong> <%= i.getTitulo() %></p>
        <p><strong>Descripción:</strong> <%= i.getDescripcion() %></p>
        <p><strong>Fecha:</strong> <%= i.getFechaCreacion() %></p>
        <p><strong>Estado:</strong> <%= i.getEstado() %></p>
    </div>
<%
        }
    }
%>

  <main>
    <section class="dashboard-hero">
      <div class="container">
        <p class="eyebrow">Área cliente</p>
        
        <p>
          Aquí puedes consultar el estado general de tu propiedad, tus servicios activos,
          incidencias y reportes más recientes.
        </p>
      </div>
    </section>

    <section class="section">
      <div class="container">
        <div class="dashboard-stats">
          <article class="dashboard-stat">
            <span class="stat-label">Plan activo</span>
            <strong>Pack Básico Plus</strong>
            <p>Supervisión, limpieza y jardinería incluidas.</p>
          </article>

          <article class="dashboard-stat">
            <span class="stat-label">Próxima supervisión</span>
            <strong>12 de abril</strong>
            <p>Revisión general programada de la vivienda.</p>
          </article>

          <article class="dashboard-stat">
            <span class="stat-label">Incidencias abiertas</span>
            <strong>1</strong>
            <p>Seguimiento técnico actualmente en curso.</p>
          </article>

          <article class="dashboard-stat">
            <span class="stat-label">Último reporte</span>
            <strong>3 de abril</strong>
            <p>Informe visual mensual disponible.</p>
          </article>
        </div>
      </div>
    </section>

    <section class="section section-alt">
      <div class="container dashboard-grid">
        <article class="dashboard-panel">
          <h2>Mi propiedad</h2>
          <ul class="dashboard-list">
            <li><strong>Villa asignada:</strong> Andryala 12</li>
            <li><strong>Ubicación:</strong> Oropesa del Mar</li>
            <li><strong>Estado general:</strong> Correcto</li>
            <li><strong>Última revisión:</strong> 3 de abril</li>
          </ul>
        </article>

        <article class="dashboard-panel">
          <h2>Servicios activos</h2>
          <ul class="dashboard-list">
            <li>Supervisión periódica</li>
            <li>Limpieza semanal</li>
            <li>Jardinería semanal</li>
            <li>Gestión de consumibles</li>
          </ul>
        </article>

        <article class="dashboard-panel">
          <h2>Incidencias recientes</h2>
          <div class="dashboard-item">
            <h3>Revisión de climatización</h3>
            <p>Estado: En curso</p>
          </div>
          <div class="dashboard-item">
            <h3>Ajuste de fontanería exterior</h3>
            <p>Estado: Pendiente de visita técnica</p>
          </div>
        </article>

        <article class="dashboard-panel">
          <h2>Últimos reportes</h2>
          <ul class="dashboard-list">
            <li>Informe visual mensual</li>
            <li>Revisión general de instalaciones</li>
            <li>Estado de jardín y exteriores</li>
          </ul>
        </article>
      </div>
    </section>

    <section class="cta">
      <div class="container cta-box">
        <h2>¿Necesitas un servicio adicional?</h2>
        <p>
          Solicita nuevos servicios o contacta con nuestro equipo para una atención personalizada.
        </p>
        <a href="contacto.html" class="btn btn-primary">Solicitar servicio</a>
      </div>
    </section>
  </main>

  <footer class="site-footer">
    <div class="container footer-grid">
      <div>
        <h3>AA Property Services</h3>
        <p>Servicios exclusivos para el cuidado integral de propiedades.</p>
      </div>

      <div>
        <h4>Enlaces</h4>
        <ul>
          <li><a href="index.html">Inicio</a></li>
          <li><a href="servicios.html">Servicios</a></li>
          <li><a href="sobre-nosotros.html">Sobre nosotros</a></li>
          <li><a href="contacto.html">Contacto</a></li>
        </ul>
      </div>

      <div>
        <h4>Ubicación</h4>
        <p>Oropesa del Mar</p>
      </div>

      <div>
        <h4>Contacto</h4>
        <p>info@aapropertyservices.com</p>
        <p>+34 601 927 601</p>
      </div>
    </div>
  </footer>

<script src="js/menu.js"></script>

</body>
</html>