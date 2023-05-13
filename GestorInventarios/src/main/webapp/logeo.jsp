

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso</title>
        <link href="logeocss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <form method="post" action="login">
            
            <label for="nombre">Número de Identificación: </label>
            <input type="number" id="identi" name="identi" placeholder="ej: 34325231" autocomplete="off"><br><br>

            <label for="contrasena">Contraseña: </label>
            <input type="password" id="contrasena" name="contrasena" placeholder="ej: 1234" autocomplete="off"><br><br>
            
            <button type="submit">Iniciar sesion</button>
            <p><a href="registro.html">Registrarse</a></p>
        </form>
    </body>
</html>
