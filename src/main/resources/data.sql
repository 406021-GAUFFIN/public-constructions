-- Insert data into DOCUMENTATION_TYPE
INSERT INTO DOCUMENTATION_TYPE (id, name, description)
VALUES (100, 'Plano Arquitectónico', 'Planos del proyecto de construcción');
INSERT INTO DOCUMENTATION_TYPE (id, name, description)
VALUES (101, 'Informacion de Seguro', 'Detalles del seguro relacionado con la construcción');

-- Insert specialties with names and descriptions in Spanish
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description)
VALUES (800, 'Electricista', 'Electricista con certificación');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description)
VALUES (801, 'Fontanero', 'Fontanero con certificación');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description)
VALUES (802, 'Topografo', 'Topógrafo especializado en levantamientos y mediciones');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description)
VALUES (803, 'Perito de Cantidades', 'Perito especializado en medición de obras y costos');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description)
VALUES (804, 'Diseñador de Interiores',
        'Diseñador de interiores especializado en planificación y decoración de espacios');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description)
VALUES (805, 'Arquitecto Paisajista', 'Arquitecto paisajista especializado en diseño de espacios exteriores');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description)
VALUES (806, 'Otro', 'Otro tipo de especialidad no categorizada');
INSERT INTO WORKER_SPECIALITY_TYPE (id, name, description)
VALUES (807, 'Albañil', 'Trabajador especializado en labores manuales pesadas en sitios de construcción');

-- Insert contructions

INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE,
                          DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS)
VALUES (1000, 101, '2024-01-15', '2024-01-20', '2024-12-01', NULL,
        'Construcción de vivienda unifamiliar de dos plantas.', TRUE, 'Casa Familiar López', 'Calle Falsa 123',
        'IN_PROGRESS');

INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE,
                          DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS)
VALUES (1001, 102, '2024-03-01', NULL, '2025-03-01', NULL, 'Edificio comercial de oficinas.', FALSE,
        'Edificio Comercial Pérez', 'Av. Principal 456', 'PLANNED');

INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE,
                          DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS)
VALUES (1002, 103, '2023-05-10', '2023-05-15', '2023-11-30', '2023-12-05', 'Ampliación de local comercial.', TRUE,
        'Local Comercial García', 'Calle del Sol 789', 'COMPLETED');

INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE,
                          DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS)
VALUES (808, 1, '2024-10-01', NULL, '2025-10-01', NULL, 'New office building', TRUE, 'Office Building A',
        '123 Main St, City', 'PLANNED');

-- Insert data into WORKER
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, SPECIALITY_TYPE, CONSTRUCTION_ID, AVAIBLE_TO_WORK)
VALUES(1, 'Siempre Viva 123', 'Homero', 'Simpson', 20324345676, NULL, 800, 808, TRUE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, SPECIALITY_TYPE, CONSTRUCTION_ID, AVAIBLE_TO_WORK)
VALUES(2, 'Estadio Libertadores de America', 'Ricardo Enrique', 'Bochini', 20777777776, NULL, 805, 808, TRUE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, SPECIALITY_TYPE, CONSTRUCTION_ID, AVAIBLE_TO_WORK)
VALUES(3, 'Esquina Libertad', 'Ciro', 'Martinez', 203152045276, NULL, 807, 1000, FALSE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, SPECIALITY_TYPE, CONSTRUCTION_ID, AVAIBLE_TO_WORK)
VALUES(4, 'Av.Colon 10500', 'Mario Alberto', 'Kempes', 20224059876, NULL, 806, 1001, TRUE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, SPECIALITY_TYPE, CONSTRUCTION_ID, AVAIBLE_TO_WORK)
VALUES(3, 'Miami', 'Leonel Andres', 'Messi', 20334568976, NULL, 804, 1001, FALSE);

-- Insert data into NOTES
INSERT INTO note (description, user_id, construction_id)
VALUES ('Initial meeting with the client.', 1, 1001);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Reviewed site plans and submitted adjustments.', 2, 1001);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Site inspection completed. No issues found.', 3, 1002);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Budget approved by finance.', 4, 1002);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Safety inspection passed.', 5, 1000);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Materials delivered to site.', 1, 1000);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Started excavation work.', 2, 808);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Electrical system design finalized.', 3, 808);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Plumbing installation in progress.', 4, 808);
INSERT INTO note (description, user_id, construction_id)
VALUES ('Roof framework completed.', 5, 808);
