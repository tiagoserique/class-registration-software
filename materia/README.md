# Tipos de armazenamento de matéria

## Materia

Classe para representar uma matéria, tendo os seguintes campos:

- `Código do curso. (codCurso)`
- `Versão da grade 2011/2019. (numVersao)`
- `Estrutura da matéria. (descrEstrutura)`
- `Codigo da disciplina. Ex. CI1001 (codDisci)`
- `Nome da unidade. Ex. Curso de Ciência da Computação - Bacharelado (nomeUnidade)`
- `Nome da disciplina. (nomeDisci)`
- `Periodo previsto para matéria. (periodo)`
- `Quantidade de horas por semestre. (numHoras)`
- `Tipo da matéria. Ex. Obrigatória. (tipoDisci)`
- `Carga horária total da matéria. (chTotal)`
- `Situação da matéria. Ex. ATIVA. (situacao)`

## Lista

Lista generica que implementa o tipo iterator, tendo os seguintes métodos:

- `Lista()
Construtor`
- `hasNext()
Por ser iterator, ver se a um próximo item`
- `next()
Retorna o elemento atual da lista e
avança contador interno para o próximo elemento`
- `Iterator()
Retorna a própria lista e reinicia o elemento atual para o primeiro`
- `insere(T elemento)/insere(int index, T elemento)
Insere elemento passado na lista, caso index seja usado, naquele index,
caso contrário no final`
- `remove(T elemento)
Remove aquele elemento da lista
retorna true caso o elemento tenha sido removido
retoran false caso contrário`
