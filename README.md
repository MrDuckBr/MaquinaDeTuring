# Maquina de Turing - JAVA

### Trabalho de Teoria da Computação - UFLA - 2021
#### Este projeto é a implementação de uma Máquina de turing quintupla utilizando na disciplina.

## Informações sobre o projeto

#### O modelo da máquina implementada é uma quintupla definida por:
``` 
M = (Q, Σ, Γ, δ, q0) : 
```
#### Onde é especificado da seguinte forma:
```
Q = conjunto de estados (padrão q[0 − 9] + )
Σ = alfabeto de entrada ( {1} para representação unária dos argumentos numéricos)
Γ = alfabeto da fita( {1, B} o símbolo branco fará a separação entre os argumentos
numéricos na fita
δ = função de transição no formato (qi ,x ) -> (qj, y, D).  assim, estando no estado qi,
lendo x, vai para o estado qj, escreve y e movimenta na direção D, 
onde D pode ser L (para esquerda) ou R ( para direita).
q = estado inicial
```

#### Exemplo de arquivo a qual a maquina fará a leitura
```
(
{q0, q1, q2, q3, q4, q5},
{1},
{1,B},
{
(q0, B) -> (q1, B, R),
(q1, 1) -> (q1, 1, R),
(q1, B) -> (q2, 1, R),
(q2, 1) -> (q2, 1, R),
(q2, B) -> (q3, B, L),
(q3, 1) -> (q4, B, L),
(q4, 1) -> (q5, B, L),
(q5, 1) -> (q5, 1, L)
}
{q0}
)
B1111B11B
```

#### Tendo o arquivo de exemplo usado a saida esperada será:
```
{q0}B1111B11B
B{q1}1111B11B
B1{q1}111B11B
B11{q1}11B11B
B111{q1}1B11B
B1111{q1}B11B
B11111{q2}11B
B111111{q2}1B
B1111111{q2}B
B111111{q3}1B
B11111{q4}1BB
B1111{q5}1BBB
B111{q5}11BBB
B11{q5}111BBB
B1{q5}1111BBB
B{q5}11111BBB
{q5}B11111BBB
```

## Usando o programa:

#### **Compilar**
```
javac Principal.java
```

#### **Executar**
```
java Principal
```
