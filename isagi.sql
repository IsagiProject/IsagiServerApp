-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-02-2023 a las 10:20:34
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `isagi`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fichajes`
--

CREATE TABLE `fichajes` (
  `id_fichaje` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_inicio` datetime NOT NULL DEFAULT current_timestamp(),
  `fecha_fin` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `fichajes`
--

INSERT INTO `fichajes` (`id_fichaje`, `id_usuario`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 3, '2023-02-04 10:33:53', '2023-02-04 10:33:55'),
(2, 3, '2023-02-04 10:34:20', '2023-02-04 10:34:22'),
(3, 3, '2023-02-04 10:34:23', '2023-02-04 10:34:26'),
(4, 3, '2023-02-04 10:34:28', '2023-02-04 10:34:30'),
(8, 3, '2023-02-04 10:52:57', '2023-02-04 10:53:13'),
(9, 3, '2023-02-10 08:14:52', '2023-02-10 08:14:59'),
(10, 3, '2023-02-10 08:15:25', '2023-02-10 08:15:29'),
(11, 3, '2023-02-10 08:15:33', '2023-02-10 08:15:35'),
(14, 3, '2023-02-14 08:20:31', '2023-02-14 08:35:30'),
(15, 8, '2023-02-14 08:36:08', '2023-02-14 15:35:37'),
(16, 8, '2023-02-15 07:35:37', '2023-02-15 13:35:37'),
(17, 7, '2023-02-14 11:50:39', '2023-02-14 11:50:41'),
(18, 8, '2023-02-14 12:16:03', '2023-02-14 12:16:07'),
(19, 8, '2023-02-14 12:16:33', '2023-02-14 12:16:37'),
(20, 8, '2023-02-14 12:16:46', '2023-02-14 12:16:48');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `cod_usu` int(11) NOT NULL,
  `nom_usu` varchar(50) NOT NULL,
  `ape_usu` varchar(50) DEFAULT NULL,
  `mail` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `categoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`cod_usu`, `nom_usu`, `ape_usu`, `mail`, `password`, `categoria`) VALUES
(3, 'Antonio', 'Perez', 'camarero@gmail.com', 'camarero@gmail.com', 'Camarero'),
(7, 'Juan', 'Garcia', 'gerente@gmail.com', 'gerente@gmail.com', 'Gerente'),
(8, 'Pepe', 'Lopez', 'pepe@gmail.com', 'pepe@gmail.com', 'Cocinero');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `fichajes`
--
ALTER TABLE `fichajes`
  ADD PRIMARY KEY (`id_fichaje`),
  ADD KEY `FK_id_usuario` (`id_usuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`cod_usu`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `fichajes`
--
ALTER TABLE `fichajes`
  MODIFY `id_fichaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `cod_usu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `fichajes`
--
ALTER TABLE `fichajes`
  ADD CONSTRAINT `FK_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`cod_usu`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
