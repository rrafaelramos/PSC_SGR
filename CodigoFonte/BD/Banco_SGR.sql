CREATE DATABASE `sgr`;

ALTER DATABASE `sgr` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci; 

USE sgr;

create table Individuo(
    id int auto_increment,
    nome varchar(50),
    primary key(id)
);

create table Email(
    id int auto_increment,
    email varchar(50),
    individuo int,
    unique(`id`),
    primary key(email,individuo),
    foreign key(individuo) references Individuo(id) on delete cascade
);

create table Telefone(
    id int auto_increment,
    telefone varchar(11),
    individuo int,
    unique(`id`),
    primary key(telefone,individuo),
    foreign key(individuo) references Individuo(id) on delete cascade
);

create table Usuario(
    individuo int,
    username varchar(50) binary not null,
    password varchar(50) not null,
    primary key (username),
    foreign key (individuo) references Individuo(id) on delete cascade
);

create table Endereco(
    id int auto_increment,
    rua varchar(50),
    num int,
    bairro varchar(50),
    cidade varchar(50),
    uf int,
    complemento varchar(200),
    primary key(id)
);

create table Cliente(
    individuo int,
    usuario int,
    cpf varchar(11) not null,
    endereco int,
    obs varchar(400),
    primary key(cpf, usuario),
    foreign key (individuo) references Individuo(id) on delete cascade,
    foreign key (endereco) references Endereco(id) ,
    foreign key (usuario) references Usuario(individuo)
);

create table Fornecedor(
    individuo int,
    usuario int,
    cnpj varchar(14),
    primary key(cnpj,usuario),
    foreign key (usuario) references Usuario(individuo),
    foreign key (individuo) references Individuo(id) on delete cascade
);

create table Produto(
    id int auto_increment,
    usuario int,
    descricao varchar(50),
    estoque int,
    custo float,
    lucro float,
    unique(id),
    primary key(usuario,descricao),
    foreign key(usuario) references Usuario(individuo)
);

create table Acontecimento(
    id int auto_increment,
    dataPrevisto timestamp,
    status int,
    usuario int,
    obs varchar(400),
    primary key(id),
    foreign key(usuario) references Usuario(individuo) 
);

create table Compromisso(
    acontecimento int,
    dataTermino timestamp,
    endereco int,
    tipo int,
    primary key(acontecimento),
    foreign key(acontecimento) references Acontecimento(id) on delete cascade,
    foreign key(endereco) references Endereco(id) 
);

create table CompromissoIndividuo(
    individuo int,
    compromisso int,
    primary key(individuo,compromisso),
    foreign key(individuo) references Individuo(id) on delete cascade,
    foreign key(compromisso) references Compromisso(acontecimento) on delete cascade
);

create table Pedido(
    acontecimento int,
    dataRealizado timestamp,
    diferenca float,
    primary key(acontecimento),
    foreign key(acontecimento) references Acontecimento(id) on delete cascade
);

create table PedidoProduto(
    id int auto_increment,
    produto int,
    pedido int,
    qtd int,
    valor float,
    unique(id),
    primary key(produto,pedido),
    foreign key(produto) references Produto(id),
    foreign key(pedido) references Pedido(acontecimento) on delete cascade
);

create table PedidoRealizado(
    pedido int,
    fornecedor int,
    primary key(pedido),
    foreign key(pedido) references Pedido(acontecimento) on delete cascade,
    foreign key(fornecedor) references Fornecedor(individuo)
);

create table PedidoRecebido(
    pedido int,
    cliente int,
    enderecoEntrega int,
    primary key(pedido),
    foreign key(pedido) references Pedido(acontecimento) on delete cascade,
    foreign key (cliente) references Cliente(individuo),
    foreign key (enderecoEntrega) references Endereco(id)
);

create table MovimentacaoMonetaria(
    acontecimento int,
    valor float,
    primary key (acontecimento),
    foreign key (acontecimento) references Acontecimento(id) on delete cascade
);

create table GastoRealizado(
    movimentacaoMonetaria int,
    descricao varchar(50),
    primary key(movimentacaoMonetaria),
    foreign key(movimentacaoMonetaria) references MovimentacaoMonetaria(acontecimento) on delete cascade
);

create table Parcela(
    movimentacaoMonetaria int,
    pedido int,
    primary key(movimentacaoMonetaria),
    foreign key(movimentacaoMonetaria) references MovimentacaoMonetaria(acontecimento) on delete cascade,
    foreign key(pedido) references Pedido(acontecimento) on delete cascade
);