# Proyecto Spring Boot

## Requisitos previos

Este proyecto requiere Oracle Database XE 21c y Java 17 o superior.

## Instalaci車n de Oracle Database XE 21c

1. Descarga e instala [Oracle Database XE 21c](https://www.oracle.com/database/technologies/xe-downloads.html)
2. Sigue las instrucciones de instalaci車n seg迆n tu sistema operativo
3. Aseg迆rate de que el servicio de Oracle est谷 ejecut芍ndose

## Configuraci車n de Base de Datos

### 1. Conectar como SYSDBA

Abre SQL*Plus desde la l赤nea de comandos y con谷ctate como SYSDBA:

```bash
sqlplus / as sysdba
```

### 2. Crear usuario de base de datos

Ejecuta los siguientes comandos en SQL*Plus (reemplaza `NOMBRE_USUARIO` y `Contrase?a` con tus credenciales deseadas):

```sql
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER NOMBRE_USUARIO IDENTIFIED BY "Contrase?a"
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

ALTER USER NOMBRE_USUARIO QUOTA UNLIMITED ON USERS;

GRANT CREATE SESSION TO NOMBRE_USUARIO;

GRANT "RESOURCE" TO NOMBRE_USUARIO;

ALTER USER NOMBRE_USUARIO DEFAULT ROLE "RESOURCE";
```

### Ejemplo con credenciales espec赤ficas:

```sql
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER DEVJOSE IDENTIFIED BY "admin"
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

ALTER USER DEVJOSE QUOTA UNLIMITED ON USERS;

GRANT CREATE SESSION TO DEVJOSE;

GRANT "RESOURCE" TO DEVJOSE;

ALTER USER DEVJOSE DEFAULT ROLE "RESOURCE";
```

### 3. Ejecutar scripts de base de datos

1. Abre Oracle SQL Developer
2. Crea una nueva conexi車n con las credenciales del usuario creado:
   - **Usuario:** DEVJOSE (o el nombre que hayas usado)
   - **Contrase?a:** admin (o la contrase?a que hayas usado)
   - **Hostname:** localhost
   - **Puerto:** 1521
   - **SID:** XE
3. Con谷ctate y ejecuta el script de base de datos proporcionado previamente

## Configuraci車n de Variables de Entorno

### 1. Archivo para desarrollo: `.env.development`

Crea un archivo llamado `.env.development` en la ra赤z del proyecto con el siguiente contenido:

```env
ALLOWED_ORIGINS=http://localhost:5173
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//localhost:1521/XE
DB_USERNAME=DEVJOSE
DB_PASSWORD=admin
```

### 2. Archivo para producci車n: `.env.production`

Crea un archivo llamado `.env.production` en la ra赤z del proyecto con el siguiente contenido:

```env
ALLOWED_ORIGINS=IP O DNS DE PRODUCCION
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//IP-DNS:1521/XE
DB_USERNAME=USUARIO
DB_PASSWORD=clave
```

**Nota:** Aseg迆rate de reemplazar `DEVJOSE` y `admin` con las credenciales que creaste en el paso anterior.

## Configuracion inicial

### Ejecutar en modo desarrollo:

```application.properties
spring.profiles.active=development
```

### Ejecutar en modo producci車n:

```application.properties
spring.profiles.active=production
```

## Notas importantes

- Los archivos `.env.development` y `.env.production` contienen informaci車n sensible y no deben ser committeados al repositorio
- Verifica que Oracle Database XE est谷 ejecut芍ndose antes de iniciar la aplicaci車n
- El puerto por defecto de la aplicaci車n Spring Boot es 8080
- Aseg迆rate de tener Java 17 o superior instalado

## Soluci車n de problemas comunes

- **Error de conexi車n a Oracle:** Verifica que el servicio Oracle est谷 ejecut芍ndose y que el puerto 1521 est谷 disponible
- **Usuario no encontrado:** Aseg迆rate de haber ejecutado correctamente los comandos SQL para crear el usuario
- **Permisos insuficientes:** Verifica que el usuario tenga los roles RESOURCE y CREATE SESSION asignados