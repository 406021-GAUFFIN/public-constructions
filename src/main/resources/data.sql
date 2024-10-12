-- Insert data into DOCUMENTATION_TYPE
INSERT INTO DOCUMENTATION_TYPE (id, name, description) VALUES (100, 'Plano Arquitectónico', 'Planos del proyecto de construcción');
INSERT INTO DOCUMENTATION_TYPE (id, name, description) VALUES (101, 'Informacion de Seguro', 'Detalles del seguro relacionado con la construcción');

-- Insert specialties with names and descriptions in Spanish
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (800, 'Electricista', 'Electricista con certificación');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (801, 'Fontanero', 'Fontanero con certificación');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (802, 'Topografo', 'Topógrafo especializado en levantamientos y mediciones');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (803, 'Perito de Cantidades', 'Perito especializado en medición de obras y costos');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (804, 'Diseñador de Interiores', 'Diseñador de interiores especializado en planificación y decoración de espacios');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (805, 'Arquitecto Paisajista', 'Arquitecto paisajista especializado en diseño de espacios exteriores');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (806, 'Otro', 'Otro tipo de especialidad no categorizada');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (807, 'Albañil', 'Trabajador especializado en labores manuales pesadas en sitios de construcción');

-- Insert contructions

INSERT INTO CONSTRUCTION (ID,  PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE, DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS)
VALUES (1,  101, '2024-01-15', '2024-01-20', '2024-12-01', NULL, 'Construcción de vivienda unifamiliar de dos plantas.', TRUE, 'Casa Familiar López', 'Calle Falsa 123', 'IN_PROGRESS');

INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE, DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS)
VALUES (2, 102, '2024-03-01', NULL, '2025-03-01', NULL, 'Edificio comercial de oficinas.', FALSE, 'Edificio Comercial Pérez', 'Av. Principal 456', 'PLANNED');

INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE, DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS)
VALUES (3, 103, '2023-05-10', '2023-05-15', '2023-11-30', '2023-12-05', 'Ampliación de local comercial.', TRUE, 'Local Comercial García', 'Calle del Sol 789', 'COMPLETED');


-- Insert data into CONSTRUCTION
INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE, DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS) VALUES (808, 1, '2024-10-01', NULL, '2025-10-01', NULL, 'New office building', TRUE, 'Office Building A', '123 Main St, City', 'PLANNED');


