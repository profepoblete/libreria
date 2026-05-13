-- CATEGORIAS
INSERT INTO categorias (descripcion) VALUES ('Programación');
INSERT INTO categorias (descripcion) VALUES ('Base de Datos');
INSERT INTO categorias (descripcion) VALUES ('Arquitectura');

-- LIBROS
INSERT INTO libros (titulo, autor, precio, categoria_id) VALUES
('Spring Boot desde cero', 'Juan Pérez', 25000, 1),
('MySQL práctico', 'Ana Torres', 18000, 2),
('Clean Architecture', 'Robert C. Martin', 30000, 3);