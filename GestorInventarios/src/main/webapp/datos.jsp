<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registros</title>
        <link href="datoscss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            
            String dat = (String) request.getSession().getAttribute("datos");
            String tot = (String) request.getSession().getAttribute("totalIn");
        %>
        
        <table>
            <tr>
                <th>Tipo</th>
                <th>Nombre</th>
                <th>Cantidad</th>
                <th>vencimiento</th>
                <th>Identificacion</th>
            </tr>
            <%= dat%>
                        
        </table>
         
            <table>
            <tr>
                <th>Total Enseres</th>
                <th>Total Alimentos</th>
                <th>Total Medicamentos</th>
            </tr>
            <%= tot%>
                        
        </table>
        
        <form method="POST" action="regisInventario.jsp">
            <button class="boton" type="submit">Otro articulo</button>
        </form>
            
        <form method="POST" action="adicionInv">
            <label for="eliminar">Eliminar elemento por Identificacion</label>
            <input type="text" name="eliminar" id="eliminar" placeholder="Ingrese texto aquí" autocomplete="off">
            
        </form>
        
        <form method="POST" action="elimina">
            
            <label for="tInventario">Tipo de articulo</label>
            <select id="tInventario" name="tInventario">
                <option></option>
                <option value="Medicamento">Medicamento</option>
                <option value="Alimento">Alimento</option>
                <option value="Enseres">Enseres</option>
            </select>
            
            <label for="delCantidad">Cantidad a añadir o eliminar</label>
            <input type="number" name="delCantidad" id="delCantidad" placeholder="Ingrese texto aquí" autocomplete="off">
            
            <label for="cBarras">Numero de identificacion</label>
            <input type="text" name="cBarras" id="cBarras" placeholder="Ingrese texto aquí" autocomplete="off">
            
            <label for="inf">Si se desea restar la cantidad se debe poner el número acompañado de un "-"</label>
            
            <div class="contenedor">
            <button class="boton" type="submit" value="ana" name="ana">Añadir</button>
            <button class="boton" type="submit" value="res" name="res">Restar</button>
            </div>
        </form>
            
        <form method="POST" action="busca">
            
            <label for="buscar" >Buscar por</label>
            <select id="buscar" name="buscar" onchange="campos()" required>
                <option disabled selected value="" >-Seleccione busqueda-</option>
                <option value="tipo_producto">Tipo de articulo</option>
                <option value="nombre">Nombre</option>
                <option value="codigo">Identificacion</option>
            </select>
            
            <div id="tipoAnimalSelect">
                <select id="buscarTipo" name="buscarTipo">
                    <option></option>
                    <option value="Medicamento">Medicamento</option>
                    <option value="Alimento">Alimento</option>
                    <option value="Enseres">Enseres</option>
                </select>
            </div>
            
            <div id="otro">
                <input type="text" name="buscarTxt" id="buscarTxt" placeholder="Ingrese texto aquí" autocomplete="off">
            </div>
            
            <button type="submit">Buscar</button>
            <br><label for="informacion">Si ya realizo la busqueda y quiere ver la tabla completa haga lo siguiente
                <br>1. Otro Articulos<br>2. Ver</label>
        </form>
            
        <script>
                
            function campos() {
                var buscar = document.getElementById("buscar").value;

                if(buscar === "tipo_producto"){
                  document.getElementById("tipoAnimalSelect").style.display = "block";
                  document.getElementById("otro").style.display = "none";
                  
                }else if (buscar === "nombre" || buscar === "codigo"){
                  document.getElementById("tipoAnimalSelect").style.display = "none";
                  document.getElementById("otro").style.display = "block";
                  
                }else{
                  document.getElementById("tipoAnimalSelect").style.display = "none";
                  document.getElementById("otro").style.display = "none";
                    
                }
            }
        </script>
    </body>  
</html>
