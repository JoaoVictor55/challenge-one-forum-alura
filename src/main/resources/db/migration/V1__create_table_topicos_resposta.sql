create table tbl_topicos(

    id bigint not null auto_increment,
    titulo varchar(100) unique not null,
    mensagem varchar(255) unique not null,
    dataCriacao Date not null,
    statusTopico varchar(255) not null,
    autor bigint not null,
    curso bigint not null,

    constraint topicosPK primary key(id)
);

create table tbl_cursos(

    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    categoria bigint,

    constraint cursosPK primary key(id)
);

create table tbl_categorias(

    id bigint not null auto_increment,
    nome varchar(100) not null unique,

    constraint categoriasPK primary key(id)
);

create table tbl_usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    email varchar(100) not null unique,
    senha varchar(100) not null,

    constraint usuariosPK primary key(id)
);

create table tbl_respostas(

    id bigint not null auto_increment,
    mensagem varchar(255) not null,
    autor bigint not null,
    solucao boolean not null,
    topico bigint not null,
    dataCriacao Date not null, 


    constraint respostasPK primary key(id)
);

alter table tbl_topicos
add constraint topicos_autorFK foreign key(autor)
references tbl_usuarios(id)
on update cascade;

alter table tbl_topicos
add constraint topicos_cursoFK foreign key(curso)
references tbl_cursos(id)
on update cascade;

alter table tbl_cursos
add constraint cursos_categoriasFK foreign key(categoria)
references tbl_categorias(id)
on update cascade;

alter table tbl_respostas
add constraint respostas_autorFK foreign key(autor)
references tbl_usuarios(id)
on update cascade;

alter table tbl_respostas
add constraint respostas_topicosFK foreign key(topico)
references tbl_topicos(id)
on update cascade;