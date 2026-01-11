# SeatSeller

**SeatSeller** é uma aplicação desenvolvida em Java para gerir reservas de lugares em cinemas de forma eficiente. Disponibiliza interfaces gráfica (JavaFX) e de consola, suportando diferentes tipos de utilizadores e funcionalidades avançadas de gestão de sessões.

This README is also available in [English](README.md)

**Projeto desenvolvido por:** 
[Guilherme Soares](https://github.com/guimbreon) && [Vitória Correia](https://github.com/vitoriateixeiracorreia)

---

## Funcionalidades

### 👤 Tipos de Utilizador & Autenticação

* Suporte a vários tipos de utilizadores: **Administradores**, **Funcionários** e **Clientes Finais**.
* Sistema de login seguro com acesso às funcionalidades baseado no perfil do utilizador.

### 🎟️ Gestão de Grelhas de Lugares

* Os administradores podem criar e configurar grelhas de lugares para diferentes salas de cinema.
* Vários tipos de lugares disponíveis:

  * Normal
  * VIP
  * Love Seat
  * Acessível
* Cada tipo de lugar possui propriedades e preços personalizados.

### 📅 Sistema de Reservas

* Os clientes (ou funcionários em seu nome) podem:

  * Selecionar sessões
  * Escolher lugares
  * Efetuar reservas
* As reservas requerem validação de cartão de crédito.
* Cada reserva gera um **código único**.

### 🔔 Notificações

* Os funcionários podem ser associados a salas específicas.
* Recebem notificações em tempo real sempre que são feitas reservas.

### ⚙️ Configuração

* O projeto utiliza um sistema centralizado de gestão de configurações e propriedades.

---

## Tecnologias Utilizadas

* Java 8 ou superior
* JavaFX para a interface gráfica
* Interface de consola (CLI)
* Padrões de projeto **Singleton** e **MVC**

---

## Como Começar

### Pré-requisitos

* Java Development Kit (JDK) 8 ou superior
* Maven ou Gradle (opcional, para gestão de dependências)

---

## Executar a Aplicação

### Versão Gráfica (GUI)

1. Navegar para a diretoria `gui`
2. Executar a classe principal:

```bash
java gui.Main
```

### Versão de Consola

1. Navegar para a diretoria `console`
2. Executar a classe principal:

```bash
java console.Main
```

---

## Estrutura do Projeto

* `domain/` – Lógica principal do domínio (utilizadores, reservas, tipos de lugares, configuração, etc.)
* `gui/` – Interface gráfica baseada em JavaFX
* `console/` – Aplicação baseada em consola e rotinas de arranque
* `domain/api/` – Interfaces que definem as operações e handlers do sistema

---

## Utilizadores de Exemplo (Criados no Arranque)

### Administrador

* **Username:** admin
* **Password:** admin

### Funcionários

* zacarias / zacarias
* serafim / serafim

### Clientes Finais

* ana / ana
* maria / maria

---

## Tipos de Lugares de Exemplo

* **Lugar Normal:** Lugar típico
* **Lugar VIP:** Lugar reclinável
* **Lugar Love Seat:** Lugar sem divisória de um dos lados
* **Lugar Acessível:** Lugar adaptado para cadeira de rodas

---

## Desenvolvedores

* **Guilherme Soares** – n62372
* **Vitória Correia** – n62211
* **Duarte Soares** – n62371
