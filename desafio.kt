fun main() {
    // Criação de alguns objetos para simular cenários de teste
    val usuario1 = Usuario("Aluno1", 1)
    val usuario2 = Usuario("Aluno2", 2)

    val conteudo1 = ConteudoEducacional("Introdução à Programação", 60)
    val conteudo2 = ConteudoEducacional("Estruturas de Dados", 90)

    val formacao = Formacao("Ciência da Computação", listOf(conteudo1, conteudo2))

    // Testando a função matricular
    formacao.matricular(usuario1, conteudo1, Nivel.BASICO)
    formacao.matricular(usuario2, conteudo2, Nivel.INTERMEDIARIO)

    // Exibindo os inscritos na formação
    println("Inscritos na formação ${formacao.nome}: ${formacao.inscritos}")
}

data class Usuario(val nomeUsuario: String, val id: Int)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario, conteudo: ConteudoEducacional, nivel: Nivel) {
        // Verifica se o conteúdo educacional faz parte da formação
        if (conteudos.contains(conteudo)) {
            // Cria uma validação específica para o nível
            val nivelPermitido = when (nivel) {
                Nivel.BASICO -> true
                Nivel.INTERMEDIARIO -> conteudo.duracao > 60
                Nivel.DIFICIL -> conteudo.duracao > 90
            }

            // Matricula o usuário se o nível for permitido
            if (nivelPermitido) {
                inscritos.add(usuario)
                println("Usuário ${usuario.nomeUsuario} matriculado na formação ${nome}")
            } else {
                println("Nível não permitido para o conteúdo ${conteudo.nome}")
            }
        } else {
            println("Conteúdo não encontrado na formação ${nome}")
        }
    }
}
