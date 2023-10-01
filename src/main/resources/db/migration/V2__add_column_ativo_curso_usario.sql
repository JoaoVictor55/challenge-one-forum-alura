use forum_alura_api;

alter table tbl_usuarios
add column ativo boolean not null default true;

alter table tbl_cursos
add column ativo boolean not null default true;