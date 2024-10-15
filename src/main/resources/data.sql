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


-- Insert data into CONSTRUCTION
INSERT INTO CONSTRUCTION (ID, PLOT_ID, PLANNED_START_DATE, ACTUAL_START_DATE, PLANNED_END_DATE, ACTUAL_END_DATE,
                          DESCRIPTION, APPROVED_BY_MUNICIPALITY, PROJECT_NAME, PROJECT_ADDRESS, CONSTRUCTION_STATUS)
VALUES (808, 1, '2024-10-01', NULL, '2025-10-01', NULL, 'New office building', TRUE, 'Office Building A',
        '123 Main St, City', 'PLANNED');

INSERT INTO DOCUMENTATION_CONSTRUCTION (ID, APPROVED, DOCUMENT_PATH, DOCUMENTATION_TYPE_ID, CONSTRUCTION_ID)
VALUES (501, TRUE, '/documents/architectural_plan.pdf', 100, 808);
INSERT INTO DOCUMENTATION_CONSTRUCTION (ID, APPROVED, DOCUMENT_PATH, DOCUMENTATION_TYPE_ID, CONSTRUCTION_ID)
VALUES (502, FALSE, '/documents/insurance_info.pdf', 101, 808);
INSERT INTO DOCUMENTATION_CONSTRUCTION (ID, APPROVED, DOCUMENT_PATH, DOCUMENTATION_TYPE_ID, CONSTRUCTION_ID)
VALUES (503, TRUE, '/documents/blueprint.pdf', 100, 808);
INSERT INTO DOCUMENTATION_CONSTRUCTION (ID, APPROVED, DOCUMENT_PATH, DOCUMENTATION_TYPE_ID, CONSTRUCTION_ID)
VALUES (504, FALSE, '/documents/insurance_update.pdf', 101, 808);
INSERT INTO DOCUMENTATION_CONSTRUCTION (ID, APPROVED, DOCUMENT_PATH, DOCUMENTATION_TYPE_ID, CONSTRUCTION_ID)
VALUES (505, TRUE, '/documents/site_survey.pdf', 100, 808);

INSERT INTO WORKER (ID, CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (501, 1001, '123 Calle Falsa', 'Juan', 'Pérez', '20-12345678-9', 'DNI12345678', 800, 808, TRUE);
INSERT INTO WORKER (ID, CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (502, 1002, '456 Calle Verdadera', 'Carlos', 'García', '20-87654321-4', 'DNI87654321', 801, 808, TRUE);
INSERT INTO WORKER (ID, CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (503, 1003, '789 Calle Real', 'María', 'López', '27-23456789-0', 'DNI23456789', 802, 808, FALSE);
INSERT INTO WORKER (ID, CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (504, 1004, '321 Avenida Siempre Viva', 'Ana', 'Rodríguez', '23-98765432-1', 'DNI98765432', 803, 808, TRUE);
INSERT INTO WORKER (ID, CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (505, 1005, '654 Calle de la Paz', 'Luis', 'Martínez', '20-76543210-5', 'DNI76543210', 804, 808, TRUE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (506, 'Siempre Viva 123', 'Homero', 'Simpson', 20324345676, NULL, 800, 808, TRUE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (507, 'Estadio Libertadores de America', 'Ricardo Enrique', 'Bochini', 20777777776, NULL, 805, 808, TRUE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (508, 'Esquina Libertad', 'Ciro', 'Martinez', 203152045276, NULL, 807, 1000, FALSE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (509, 'Av.Colon 10500', 'Mario Alberto', 'Kempes', 20224059876, NULL, 806, 1001, TRUE);
INSERT INTO WORKER (CONTACT_ID, ADDRESS, NAME, LAST_NAME, CUIL, DOCUMENT, worker_speciality_id, CONSTRUCTION_ID,
                    AVAILABLE_TO_WORK)
VALUES (510, 'Miami', 'Leonel Andres', 'Messi', 20334568976, NULL, 804, 1001, FALSE);

INSERT INTO NOTE (ID, DESCRIPTION, USER_ID, CONSTRUCTION_ID)
VALUES (501, 'Primera visita al sitio completada.', 101, 808);
INSERT INTO NOTE (ID, DESCRIPTION, USER_ID, CONSTRUCTION_ID)
VALUES (502, 'Planificación de la obra en curso.', 102, 808);
INSERT INTO NOTE (ID, DESCRIPTION, USER_ID, CONSTRUCTION_ID)
VALUES (503, 'Revisión de los planos con el municipio.', 103, 808);
INSERT INTO NOTE (ID, DESCRIPTION, USER_ID, CONSTRUCTION_ID)
VALUES (504, 'Pendiente aprobación del seguro.', 104, 808);
INSERT INTO NOTE (ID, DESCRIPTION, USER_ID, CONSTRUCTION_ID)
VALUES (505, 'Obra lista para comenzar.', 105, 808);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (506, 'Initial meeting with the client.', 1, 1001);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (507, 'Reviewed site plans and submitted adjustments.', 2, 1001);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (508, 'Site inspection completed. No issues found.', 3, 1002);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (509, 'Budget approved by finance.', 4, 1002);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (510, 'Safety inspection passed.', 5, 1000);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (511, 'Materials delivered to site.', 1, 1000);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (512, 'Started excavation work.', 2, 808);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (513, 'Electrical system design finalized.', 3, 808);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (514, 'Plumbing installation in progress.', 4, 808);
INSERT INTO NOTE (id, description, user_id, construction_id)
VALUES (515, 'Roof framework completed.', 5, 808);
