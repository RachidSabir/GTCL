<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>
<%
    long totalProjet = (long) request.getAttribute("totalProjet");
    long totalTache = (long) request.getAttribute("totalTache");
    long totalMembres = (long) request.getAttribute("totalMembres");
    long totalChefs = (long) request.getAttribute("totalChefs");
    long totalTacheOnHold = (long) request.getAttribute("totalTacheOnHold");

%>
<section class="main-content">
    <div class="wrapper">
        <ul class="items">
            <li class="item">
                <div class="inner">
                    <h4 class="name"><%=totalTache%>
                    </h4>
                    <p class="descr">Nombre de Taches</p>
                </div>
            </li>
            <li class="item">
                <div class="inner">
                    <h4 class="name"><%=totalTacheOnHold%>
                    </h4>
                    <p class="descr">Nombre de Taches en attente</p>
                </div>
            </li>
            <li class="item">
                <div class="inner">
                    <h4 class="name"><%=totalProjet%>
                    </h4>
                    <p class="escr">Nombre de projet</p>
                </div>
            </li>
            <li class="item">
                <div class="inner">
                    <h4 class="name"><%=totalChefs%>
                    </h4>
                    <p class="descr">Nombre chef de grp</p>
                </div>
            </li>
            <li class="item">
                <div class="inner">
                    <h4 class="name"><%=totalMembres%>
                    </h4>
                    <p class="escr">number membre</p>
                </div>
            </li>
        </ul>
    </div>
    <div class="static">
        <canvas id="myChart" style="width:50%;max-width:750px"></canvas>
        <canvas id="myChart1" style="width:50%;max-width:750px"></canvas>
    </div>
</section>
<script>
    var totalTache = <%= (long) request.getAttribute("totalTache") %>;
    var totalNew = <%= (long) request.getAttribute("totalNew") %>;
    var totalInProgress = <%= (long) request.getAttribute("totalInProgress") %>;
    var totalDone = <%= (long) request.getAttribute("totalDone") %>;
    var totalOnHold = <%= (long) request.getAttribute("totalTacheOnHold") %>;
    var xValues = ["NOUVEAU", "En cours", "En attente", "Terminé"];
    var yValues = [
        totalTache !== 0 ? (totalNew / totalTache) * 100 : 0,
        totalTache !== 0 ? (totalInProgress / totalTache) * 100 : 0,
        totalTache !== 0 ? (totalOnHold / totalTache) * 100 : 0,
        totalTache !== 0 ? (totalDone / totalTache) * 100 : 0
    ];
    var barColors = [
        "#b91d47",
        "#00aba9",
        "#2b5797"
    ];

    new Chart("myChart", {
        type: "doughnut",
        data: {
            labels: xValues,
            datasets: [{
                backgroundColor: barColors,
                data: yValues
            }]
        },
        options: {
            title: {
                display: true,
                text: "Distribution des Tâches selon leur Statut"
            }
        }
    });
</script>
<%@ include file="../footer/footer.jsp" %>
