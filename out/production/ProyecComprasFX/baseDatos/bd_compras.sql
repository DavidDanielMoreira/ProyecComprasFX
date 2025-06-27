-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-06-2025 a las 01:13:34
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_compras`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

CREATE TABLE `cajas` (
  `id_caja` int(10) UNSIGNED NOT NULL,
  `id_mone` int(10) UNSIGNED NOT NULL,
  `monto` double(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cajas`
--

INSERT INTO `cajas` (`id_caja`, `id_mone`, `monto`) VALUES
(1, 1, 7178.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id_cate` int(10) UNSIGNED NOT NULL,
  `nombre_cate` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id_cate`, `nombre_cate`) VALUES
(1, 'Hardware'),
(2, 'Software'),
(3, 'Accesorios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_clie` int(10) UNSIGNED NOT NULL,
  `apellido_clie` varchar(40) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombres_clie` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `domicilio_clie` varchar(90) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dni_clie` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono_clie` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo_clie` varchar(70) COLLATE utf8_spanish_ci DEFAULT NULL,
  `otros_datos_clie` varchar(300) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado_clie` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_clie`, `apellido_clie`, `nombres_clie`, `domicilio_clie`, `dni_clie`, `telefono_clie`, `correo_clie`, `otros_datos_clie`, `estado_clie`) VALUES
(1, 'CONSUMIDOR', 'FINAL', 'CONSUMIDOR FINAL', '00000000', '0000000000', '-', '-', 1),
(2, 'Diaz', 'Juan', 'Belgrano 567', '20888111', '0000000', '-', '-', 1),
(3, 'Vidal', 'Arturo', 'Urrutia 1100', '27888000', '00000', 'arturo_v@hotmail.com', 'SSS', 1),
(4, 'Solis', 'Alberto', 'Solis', '25888000', '00000', '-', 'DD', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `id_comp` int(10) UNSIGNED NOT NULL,
  `fecha_comp` date DEFAULT NULL,
  `tipo_factura_comp` varchar(1) COLLATE utf8_spanish_ci NOT NULL,
  `nro_factura_comp` varchar(12) COLLATE utf8_spanish_ci NOT NULL,
  `id_prov` int(10) UNSIGNED NOT NULL,
  `id_modo` int(10) UNSIGNED NOT NULL,
  `total` double(9,2) DEFAULT NULL,
  `estado_comp` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`id_comp`, `fecha_comp`, `tipo_factura_comp`, `nro_factura_comp`, `id_prov`, `id_modo`, `total`, `estado_comp`) VALUES
(1, '2025-06-19', 'A', '0009999', 2, 1, 16595.15, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `d_compras`
--

CREATE TABLE `d_compras` (
  `id_detc` int(10) UNSIGNED NOT NULL,
  `id_comp` int(10) UNSIGNED NOT NULL,
  `id_prod` int(10) UNSIGNED NOT NULL,
  `cantidad` double(4,2) DEFAULT NULL,
  `precio` double(8,2) DEFAULT NULL,
  `importe` double(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `d_compras`
--

INSERT INTO `d_compras` (`id_detc`, `id_comp`, `id_prod`, `cantidad`, `precio`, `importe`) VALUES
(1, 1, 3, 5.00, 3319.03, 16595.15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `d_tickets`
--

CREATE TABLE `d_tickets` (
  `id_dett` int(10) UNSIGNED NOT NULL,
  `id_tick` int(10) UNSIGNED NOT NULL,
  `id_prod` int(10) UNSIGNED NOT NULL,
  `cantidad` double(4,2) DEFAULT NULL,
  `precio` double(8,2) DEFAULT NULL,
  `importe` double(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `d_tickets`
--

INSERT INTO `d_tickets` (`id_dett`, `id_tick`, `id_prod`, `cantidad`, `precio`, `importe`) VALUES
(1, 1, 1, 1.00, 2178.00, 2178.00),
(2, 1, 2, 1.00, 3630.00, 3630.00),
(3, 2, 1, 2.00, 2178.00, 4356.00),
(4, 2, 2, 1.00, 3630.00, 3630.00),
(5, 3, 1, 1.00, 2178.00, 2178.00),
(6, 4, 2, 1.00, 3630.00, 3630.00),
(7, 5, 1, 1.00, 2178.00, 2178.00),
(8, 6, 1, 2.00, 5445.00, 10890.00),
(9, 7, 1, 1.00, 5445.00, 5445.00),
(10, 7, 2, 1.00, 9075.00, 9075.00),
(11, 8, 3, 2.00, 4994.00, 9988.00),
(12, 9, 3, 1.00, 4994.00, 4994.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monedas`
--

CREATE TABLE `monedas` (
  `id_mone` int(10) UNSIGNED NOT NULL,
  `simbolo_mone` varchar(6) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion_mone` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `monedas`
--

INSERT INTO `monedas` (`id_mone`, `simbolo_mone`, `descripcion_mone`) VALUES
(1, '$', 'Pesos'),
(2, 'USD', 'Dolares');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `m_pagos`
--

CREATE TABLE `m_pagos` (
  `id_modo` int(10) UNSIGNED NOT NULL,
  `tipo_pago` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `m_pagos`
--

INSERT INTO `m_pagos` (`id_modo`, `tipo_pago`) VALUES
(1, 'EN EFECTIVO'),
(2, 'CTACTE'),
(3, 'OTROS MEDIOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_prod` int(10) UNSIGNED NOT NULL,
  `codigo_prod` varchar(25) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion_corta_prod` varchar(70) COLLATE utf8_spanish_ci DEFAULT NULL,
  `codigo_barra_prod` varchar(13) COLLATE utf8_spanish_ci DEFAULT NULL,
  `precio_c` double(8,2) DEFAULT NULL,
  `margen` double(4,2) DEFAULT NULL,
  `iva` double(4,2) DEFAULT NULL,
  `precio_v` double(8,2) DEFAULT NULL,
  `stock_i` double(4,2) DEFAULT NULL,
  `stock` double(4,2) DEFAULT NULL,
  `cantidad` double(4,2) DEFAULT NULL,
  `id_mone` int(10) UNSIGNED NOT NULL,
  `id_cate` int(10) UNSIGNED NOT NULL,
  `id_prov` int(10) UNSIGNED NOT NULL,
  `id_unid` int(10) UNSIGNED NOT NULL,
  `estado_prod` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_prod`, `codigo_prod`, `descripcion_corta_prod`, `codigo_barra_prod`, `precio_c`, `margen`, `iva`, `precio_v`, `stock_i`, `stock`, `cantidad`, `id_mone`, `id_cate`, `id_prov`, `id_unid`, `estado_prod`) VALUES
(1, 'MOUSE', 'Mouse Generico', '', 3000.00, 0.50, 1.21, 5989.50, 2.00, 2.00, 0.00, 1, 1, 1, 1, 1),
(2, 'TECLADO', 'Teclado generico', '', 5000.00, 0.50, 1.21, 9982.50, 3.00, 2.00, 0.00, 1, 1, 1, 1, 1),
(3, 'CABLEHDMI2', 'Cable HDMI generico', '', 2500.00, 0.50, 1.21, 4994.00, 3.00, 2.00, 0.00, 1, 3, 2, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id_prov` int(10) UNSIGNED NOT NULL,
  `razon_social_prov` varchar(90) COLLATE utf8_spanish_ci DEFAULT NULL,
  `domicilio_prov` varchar(90) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cuit_prov` varchar(11) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono_prov` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo_prov` varchar(90) COLLATE utf8_spanish_ci DEFAULT NULL,
  `contacto_prov` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `otros_datos_prov` varchar(400) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado_prov` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id_prov`, `razon_social_prov`, `domicilio_prov`, `cuit_prov`, `telefono_prov`, `correo_prov`, `contacto_prov`, `otros_datos_prov`, `estado_prov`) VALUES
(1, 'Computación S.A', 'Alvear 123 - Rosario - Pcia. Santa Fe', '30204557773', '341565656', 'compu@gmail.com', 'Horacio Torres', '-', 1),
(2, 'Software S.A.', 'Lujan 777 Pcia. Bs. As.', '30208887772', '011455455', 'software@gmail.com.ar', '', '-', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

CREATE TABLE `tickets` (
  `id_tick` int(10) UNSIGNED NOT NULL,
  `fecha_tick` date DEFAULT NULL,
  `nro_tick` varchar(9) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipo_tick` varchar(1) COLLATE utf8_spanish_ci NOT NULL,
  `id_clie` int(10) UNSIGNED NOT NULL,
  `id_modo` int(10) UNSIGNED NOT NULL,
  `descuento` double(4,2) NOT NULL,
  `total` double(8,2) DEFAULT NULL,
  `estado_tick` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`id_tick`, `fecha_tick`, `nro_tick`, `tipo_tick`, `id_clie`, `id_modo`, `descuento`, `total`, `estado_tick`) VALUES
(1, '2025-04-26', '00000001', 'T', 1, 1, 0.00, 5808.00, 1),
(2, '2025-04-26', '00000002', 'T', 1, 1, 0.00, 7986.00, 1),
(3, '2025-05-05', '00000003', 'T', 1, 1, 0.00, 2178.00, 1),
(4, '2025-05-05', '00000004', 'T', 1, 1, 0.00, 3630.00, 1),
(5, '2025-05-05', '00000005', 'T', 1, 1, 0.00, 2178.00, 1),
(6, '2025-06-04', '00000006', 'T', 1, 1, 0.00, 10890.00, 1),
(7, '2025-06-16', '00000007', 'T', 1, 1, 0.10, 13068.00, 1),
(8, '2025-06-19', '00000008', 'T', 2, 1, 0.00, 9988.00, 1),
(9, '2025-06-20', '00000009', 'T', 1, 1, 0.10, 4494.60, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usua` int(11) UNSIGNED NOT NULL,
  `nombre_usua` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `clave_usua` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado_usua` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `u_medidas`
--

CREATE TABLE `u_medidas` (
  `id_unid` int(10) UNSIGNED NOT NULL,
  `sigla_unid` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion_unid` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `u_medidas`
--

INSERT INTO `u_medidas` (`id_unid`, `sigla_unid`, `descripcion_unid`) VALUES
(1, 'UNI', 'Unidad');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cajas`
--
ALTER TABLE `cajas`
  ADD PRIMARY KEY (`id_caja`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_cate`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_clie`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`id_comp`);

--
-- Indices de la tabla `d_compras`
--
ALTER TABLE `d_compras`
  ADD PRIMARY KEY (`id_detc`);

--
-- Indices de la tabla `d_tickets`
--
ALTER TABLE `d_tickets`
  ADD PRIMARY KEY (`id_dett`);

--
-- Indices de la tabla `monedas`
--
ALTER TABLE `monedas`
  ADD PRIMARY KEY (`id_mone`);

--
-- Indices de la tabla `m_pagos`
--
ALTER TABLE `m_pagos`
  ADD PRIMARY KEY (`id_modo`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_prod`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id_prov`);

--
-- Indices de la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id_tick`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usua`);

--
-- Indices de la tabla `u_medidas`
--
ALTER TABLE `u_medidas`
  ADD PRIMARY KEY (`id_unid`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cajas`
--
ALTER TABLE `cajas`
  MODIFY `id_caja` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id_cate` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_clie` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `id_comp` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `d_compras`
--
ALTER TABLE `d_compras`
  MODIFY `id_detc` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `d_tickets`
--
ALTER TABLE `d_tickets`
  MODIFY `id_dett` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `monedas`
--
ALTER TABLE `monedas`
  MODIFY `id_mone` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `m_pagos`
--
ALTER TABLE `m_pagos`
  MODIFY `id_modo` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_prod` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_prov` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id_tick` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usua` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `u_medidas`
--
ALTER TABLE `u_medidas`
  MODIFY `id_unid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
