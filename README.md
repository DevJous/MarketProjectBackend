# Proyecto - Sistema de Productos

Este proyecto contiene los procedimientos almacenados y configuraciones necesarias para un sistema de gestión de productos usando Oracle Database XE.

## 📋 Requisitos del Sistema

### Software Necesario
- **Oracle Database XE 21c** o superior
- **SQL Developer** (recomendado) o SQL*Plus
- **Sistema Operativo:** Windows 10/11, Linux, o macOS

### Versiones Compatibles
- Oracle XE 21c

## 🚀 Configuración Inicial de Base de Datos

### 1. Instalación de Oracle XE

1. **Descargar Oracle XE:**
   - Visita [Oracle XE Download](https://www.oracle.com/database/technologies/xe-downloads.html)
   - Descarga la versión apropiada para tu SO

2. **Instalar Oracle XE:**
   ```bash
   # Windows: Ejecutar el instalador como Administrador
   # Linux: 
   sudo dpkg -i oracle-xe_21c_amd64.deb
   sudo /etc/init.d/oracle-xe configure
   ```

### 2. Conectar como Administrador

```sql
-- Usando SQL*Plus
sqlplus sys as sysdba

-- Usando SQL Developer
Usuario: sys
Contraseña: [tu_password_sys]
Rol: SYSDBA
Hostname: localhost
Puerto: 1521
SID: XE
```

### 3. Crear Usuario de Aplicación

```sql
-- Crear usuario
CREATE USER app_productos IDENTIFIED BY "password123";

-- Otorgar privilegios
GRANT CONNECT, RESOURCE TO app_productos;
GRANT CREATE VIEW TO app_productos;
GRANT UNLIMITED TABLESPACE TO app_productos;

-- Conectar con el nuevo usuario
CONNECT app_productos/password123@localhost:1521/XE;
```


## 📁 Estructura del Proyecto

```
proyecto-oracle/
│
├── README.md                 # Este archivo
├── sql/
│   ├── 01_crear_tablas.sql  # Scripts de creación de tablas
│   ├── 02_procedimientos.sql # Procedimientos almacenados
│   ├── 03_datos_prueba.sql  # Datos de prueba
│   └── 04_consultas.sql     # Consultas de ejemplo
├── config/
│   ├── listener.ora         # Configuración del listener
│   └── tnsnames.ora         # Configuración de conexiones
└── docs/
    └── manual_usuario.md    # Manual del usuario
```

## 🔧 Instalación del Proyecto

### 1. Clonar/Descargar el Proyecto

```bash
# Si usas GitBash (O desde GitHub Desktop)
git clone https://github.com/DevJous/MarketProjectBackend.git

# O descargar y extraer el ZIP
```

### 2. Ejecutar Scripts en Orden

```sql
-- 1. Conectar como app_productos
sqlplus app_productos/password123@localhost:1521/XE

-- 2. Ejecutar scripts en orden
@sql/01_crear_tablas.sql
@sql/02_procedimientos.sql
@sql/03_datos_prueba.sql
```

### 3. Verificar Instalación

```sql
-- Verificar tablas creadas
SELECT table_name FROM user_tables;

-- Verificar procedimientos
SELECT object_name FROM user_objects WHERE object_type = 'PROCEDURE';

-- Verificar datos de prueba
SELECT COUNT(*) FROM productos;
SELECT COUNT(*) FROM categorias;
```

## 🏃‍♂️ Ejecutar el Proyecto

### Conexión con SQL Developer

1. **Nueva Conexión:**
   - Nombre de conexión: `ProductosDB`
   - Usuario: `app_productos`
   - Contraseña: `password123`
   - Hostname: `localhost`
   - Puerto: `1521`
   - SID: `XE`

2. **Probar Conexión** y hacer clic en "Conectar"

### Usar Procedimientos Almacenados

```sql
-- Actualizar un producto
EXEC SP_UpdateProductos(1, 'Laptop Gaming', 1299.99, 1);

-- Consultar productos
SELECT * FROM productos WHERE id_producto = 1;
```


## 📝 Notas Adicionales

- **Puerto por defecto:** 1521
- **Usuario SYS contraseña:** Configurada durante la instalación

## 📜 Licencia

Este proyecto utiliza Oracle XE que tiene su propia licencia gratuita para desarrollo y pruebas.

---

**Última actualización:** Agosto 2025  
**Autor:** José Franco
**Versión:** 1.0