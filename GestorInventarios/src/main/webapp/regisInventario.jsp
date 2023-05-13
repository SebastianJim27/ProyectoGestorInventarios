<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Articulos</title>
        <link href="regisinvencss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form method="post" action="adicionInv">
            
            <label for="tInventario">Tipo de articulo</label>
            <select id="tInventario" name="tInventario">
                <option></option>
                <option value="Medicamento">Medicamento</option>
                <option value="Alimento">Alimento</option>
                <option value="Enseres">Enseres</option>
            </select>
            
            <label for="pNombre">Nombre del producto:</label>
            <input type="text" id="pNombre" name="pNombre" placeholder="ej: pulgarcito" autocomplete="off">

            <label for="cantidad">Cantidad: </label>
            <input type="number" id="cantidad" name="cantidad" placeholder="ej: 3" autocomplete="off">
            
            <label for="cBarras">Identificacion del producto: </label>
            <input type="text" id="cBarras" name="cBarras" placeholder="ej: A1BC23DE" autocomplete="off">

            <label for="Vencimiento">Vencimiento: </label>
            <input type="date" id="Vencimiento" name="Vencimiento" autocomplete="off"><br>
            
            <div class="contenedor">
            <button class="boton" type="submit">Guardar</button>
            <button class="boton" type="submit">Ver </button>
            </div>
        </form>
    </body>
</html>
