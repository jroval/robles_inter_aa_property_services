

CREATE TABLE `complejo` (
  `id_complejo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ubicacion` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `complejo`
--

INSERT INTO `complejo` (`id_complejo`, `nombre`, `ubicacion`) VALUES
(1, 'Andryala', 'Oropesa del Mar'),
(2, 'Barlovento', 'Oropesa del Mar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan`
--

CREATE TABLE `plan` (
  `id_plan` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio_mensual` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plan`
--

INSERT INTO `plan` (`id_plan`, `nombre`, `descripcion`, `precio_mensual`) VALUES
(1, 'Plan Básico', 'Incluye inspecciones semanales', 100.00),
(2, 'Plan Básico Plus', 'Incluye limpieza y jardinería', 200.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_servicio`
--

CREATE TABLE `plan_servicio` (
  `id_plan` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plan_servicio`
--

INSERT INTO `plan_servicio` (`id_plan`, `id_servicio`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(2, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `id_propietario` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`id_propietario`, `nombre`, `apellidos`, `email`, `telefono`) VALUES
(1, 'Juan', 'Pérez', 'juan@email.com', '600111222'),
(2, 'Laura', 'García', 'laura@email.com', '600333444');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id_servicio` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id_servicio`, `nombre`, `categoria`, `precio`) VALUES
(1, 'Limpieza', 'Mantenimiento', 50.00),
(2, 'Jardinería', 'Mantenimiento', 60.00),
(3, 'Chef privado', 'Premium', 150.00),
(4, 'Chofer', 'Transporte', 80.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `villa`
--

CREATE TABLE `villa` (
  `id_villa` int(11) NOT NULL,
  `codigo_villa` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `id_propietario` int(11) NOT NULL,
  `id_complejo` int(11) NOT NULL,
  `id_plan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `villa`
--

INSERT INTO `villa` (`id_villa`, `codigo_villa`, `estado`, `id_propietario`, `id_complejo`, `id_plan`) VALUES
(1, 'ANDRYALA 1A', 'Activa', 1, 1, 2),
(2, 'BARLOVENTO 2B', 'Activa', 2, 2, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `complejo`
--
ALTER TABLE `complejo`
  ADD PRIMARY KEY (`id_complejo`);

--
-- Indices de la tabla `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`id_plan`);

--
-- Indices de la tabla `plan_servicio`
--
ALTER TABLE `plan_servicio`
  ADD PRIMARY KEY (`id_plan`,`id_servicio`),
  ADD KEY `fk_plan_servicio_servicio` (`id_servicio`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`id_propietario`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id_servicio`);

--
-- Indices de la tabla `villa`
--
ALTER TABLE `villa`
  ADD PRIMARY KEY (`id_villa`),
  ADD KEY `fk_villa_propietario` (`id_propietario`),
  ADD KEY `fk_villa_complejo` (`id_complejo`),
  ADD KEY `fk_villa_plan` (`id_plan`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `complejo`
--
ALTER TABLE `complejo`
  MODIFY `id_complejo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `plan`
--
ALTER TABLE `plan`
  MODIFY `id_plan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `propietario`
--
ALTER TABLE `propietario`
  MODIFY `id_propietario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `villa`
--
ALTER TABLE `villa`
  MODIFY `id_villa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `plan_servicio`
--
ALTER TABLE `plan_servicio`
  ADD CONSTRAINT `fk_plan_servicio_plan` FOREIGN KEY (`id_plan`) REFERENCES `plan` (`id_plan`),
  ADD CONSTRAINT `fk_plan_servicio_servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id_servicio`);

--
-- Filtros para la tabla `villa`
--
ALTER TABLE `villa`
  ADD CONSTRAINT `fk_villa_complejo` FOREIGN KEY (`id_complejo`) REFERENCES `complejo` (`id_complejo`),
  ADD CONSTRAINT `fk_villa_plan` FOREIGN KEY (`id_plan`) REFERENCES `plan` (`id_plan`),
  ADD CONSTRAINT `fk_villa_propietario` FOREIGN KEY (`id_propietario`) REFERENCES `propietario` (`id_propietario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
