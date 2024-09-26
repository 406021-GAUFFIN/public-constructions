-- Insert data into DOCUMENTATION_TYPE
INSERT INTO DOCUMENTATION_TYPE (id, name, description) VALUES (1, 'Plano Arquitectónico', 'Planos del proyecto de construcción');
INSERT INTO DOCUMENTATION_TYPE (id, name, description) VALUES (2, 'Informacion de Seguro', 'Detalles del seguro relacionado con la construcción');

-- Insert specialties with names and descriptions in Spanish
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (1, 'Electricista', 'Electricista con certificación');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (2, 'Fontanero', 'Fontanero con certificación');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (3, 'Topografo', 'Topógrafo especializado en levantamientos y mediciones');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (4, 'Perito de Cantidades', 'Perito especializado en medición de obras y costos');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (5, 'Diseñador de Interiores', 'Diseñador de interiores especializado en planificación y decoración de espacios');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (6, 'Arquitecto Paisajista', 'Arquitecto paisajista especializado en diseño de espacios exteriores');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (7, 'Otro', 'Otro tipo de especialidad no categorizada');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description) VALUES (8, 'Albañil', 'Trabajador especializado en labores manuales pesadas en sitios de construcción');

-- Insert data into CONSTRUCTION
INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE, DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS) VALUES (1, 1, '2024-10-01', NULL, '2025-10-01', NULL, 'New office building', TRUE, 'Office Building A', '123 Main St, City', 'PLANNED');


