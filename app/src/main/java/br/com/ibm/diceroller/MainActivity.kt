package br.com.ibm.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity // ComponentActivity está localizado dentro desse pacote
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ibm.diceroller.ui.theme.DiceRollerTheme
import br.com.ibm.diceroller.R

// Definindo a tela principal do aplicativo
// Essa parte é a "base" do aplicativo. Aqui é onde o app começa a funcionar

/*
DEFININDO A CLASSE PRINCIPAL
a) class MainActivity → Define uma classe chamada MainActivity, que representa a tela principal do aplicativo

b) : ComponentActivity() → Significa que MainActivity herda (:) de ComponentActivity.
- Isso faz com que a MainActivity seja uma atividade (Activity) do Android, ou seja, uma tela que pode ser exibida.
- O ComponentActivity vem do Jetpack, que é um conjunto de bibliotecas do Android desenvolvido pelo Google

Ou seja:
class MainActivity : ComponentActivity()
- Estamos herdando (:) funcionalidades de ComponentActivity, que está dentro da biblioteca AndroidX Activity (androidx.activity).

MÉTODO ONCREATE()
a) override fun onCreate(savedInstanceState: Bundle?) → Essa função é executada automaticamente sempre que a tela do aplicativo é criada pela primeira vez
b) override → Significa que estamos substituindo (sobrescrevendo) a versão padrão do método onCreate() que existe dentro da ComponentActivity
- No Android, onCreate() já existe dentro da ComponentActivity, mas precisamos sobrescrevê-lo para adicionar nosso próprio código
c) savedInstanceState: Bundle? → Esse parâmetro guarda o estado anterior da tela, caso o app seja minimizado ou fechado e reaberto.

CHAMANDO SUPER.ONCREATE(SAVEDINSTANCESTATE)
a) super.onCreate(savedInstanceState) → Chama a versão do método onCreate() da classe pai (ComponentActivity)

CONFIGURANDO O CONTEÚDO DA TELA
a) setContent { ... } → Define o conteúdo da tela
-  Em aplicativos tradicionais do Android (com XML), teríamos que definir a tela com setContentView(R.layout.activity_main), mas aqui usamos Compose, então a tela é criada com código Kotlin puro

APLICANDO O TEMA DO APLICATIVO
a) DiceRollerTheme { ... } → Aplica um tema visual ao aplicativo
- Isso define cores, fontes e estilos predefinidos. Sem isso, a tela teria um estilo padrão do Android

CHAMANDO A FUNÇÃO DICEROLLERAPP()
a) DiceRollerApp() → Chama a função que constrói a interface do app (onde temos a imagem do dado e o botão).
- Essa função é definida em outro lugar do código e desenha os elementos na tela.
*/

// Definição da classe principal
class MainActivity : ComponentActivity() {
    // Método onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {
        // Chamando super.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        // Configurando o conteúdo da tela
        setContent {
            // Aplicando o tema do aplicativo
            DiceRollerTheme {
                // Chamando a função DiceRollerApp()
                DiceRollerApp()
            }
        }
    }
}

/*
O QUE SIGNIFICA O @PREVIEW?
- Essa linha indica que podemos visualizar essa função dentro do Android Studio, sem precisar rodar o app no celular ou emulador

O QUE É O @COMPOSABLE?
- O @Composable significa que essa função desenha algo na tela.
- No Jetpack Compose, tudo que aparece na tela é criado dentro de uma função @Composable.
- Se esquecermos essa anotação, o código não será reconhecido como parte da interface.
- Exemplo: Um botão, um texto ou uma imagem são elementos combináveis (@Composable).

CHAMANDO DICEWITHBUTTONANDIMAGE()
a) Essa linha chama outra função chamada DiceWithButtonAndImage(), que cria os elementos visuais do app, como:
- A imagem do dado
- O botão para rolar o dado

AJUSTANDO O TAMANHO E A POSIÇÃO
- modifier → Permite personalizar tamanho, posição e aparência do layout.
- fillMaxSize() → Faz com que o layout ocupe toda a tela.
- wrapContentSize(Alignment.Center) → Centraliza os elementos na tela.

Analogia simples:
- Imagine que a tela do celular é uma folha de papel.
- fillMaxSize() → Pinta a folha inteira com uma cor de fundo.
- wrapContentSize(Alignment.Center) → Coloca os elementos (imagem e botão) bem no centro da folha.
*/

// Definindo a tela do app
// Essa função organiza o layout do aplicativo
@Preview
@Composable
// Criando a função DiceRollerApp(), que será responsável por desenhar a tela do app
// Aqui dentro, vamos definir o que será mostrado na tela
fun DiceRollerApp() {
    // Chamando DiceWithButtonAndImage()
    DiceWithButtonAndImage(
        // Ajustando o tamanho e a posição
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

/*
- modifier: Modifier = Modifier → Esse parâmetro permite personalizar o layout de fora da função, tornando o código mais flexível

VARIÁVEL DE ESTADO PÁRA ARMAZENAR O NÚMERO SORTEADO
- var result → Cria uma variável chamada result, que guarda o número sorteado do dado.
- by remember { mutableStateOf(1) } → Mantém o valor do dado mesmo quando a tela do app atualiza.
- O valor começa como 1 (ou seja, o dado começa mostrando o número 1).

PORQUE USAMOS REMEMBER?
- Se não usássemos remember, o número do dado voltaria para 1 toda vez que o app redesenhasse a tela, tornando a experiência ruim.

ESCOLHENDO A IMAGEM DO DADO
- val imageResource → Criamos uma variável chamada imageResource, que guarda a imagem do dado correspondente ao número sorteado.
- when (result) { ... } → Um tipo de if mais organizado, que escolhe qual imagem mostrar dependendo do número.

Como funciona?
- Se result == 1, então imageResource será R.drawable.dice_1 (imagem do dado com número 1).
- Se result == 2, então imageResource será R.drawable.dice_2 (imagem do dado com número 2).
- Isso se repete até o número 6.

Por que else -> R.drawable.dice_6?
- O else garante que, se houver algum erro, ele ainda exiba uma imagem válida (o dado com número 6).

// CRIANDO A INTERFACE DO USUÁRIO
- Column(...) {} → Cria um layout vertical, ou seja, coloca os elementos um embaixo do outro.
- modifier = modifier → Usa as configurações passadas na chamada da função (permite personalizar o layout externamente).
- horizontalAlignment = Alignment.CenterHorizontally → Faz com que tudo dentro da Column fique centralizado horizontalmente.

O que acontece dentro dessa Column?
- Primeiro, aparece a imagem do dado.
- Depois, um espaço entre os elementos.
- Por último, o botão para rolar o dado.

EXIBINDO A IMAGEM DO BOTÃO
Image(...) → Exibe a imagem do dado na tela.
- painterResource(imageResource) → Pega a imagem correspondente ao número sorteado.
- contentDescription = result.toString() → Isso serve para acessibilidade (leitores de tela).

Como funciona?
- Se o result for 3, então imageResource será R.drawable.dice_3, e essa imagem será exibida.

CRIANDO UM ESPAÇO ENTRE A IMAGEM E O BOTÃO
- Spacer(...) → Adiciona um espaço entre a imagem e o botão, para que eles não fiquem grudados.
- Modifier.height(16.dp) → Define a altura do espaço como 16 dp (padrão do Android).

Por que isso é importante?
- Sem Spacer, a imagem do dado e o botão ficariam muito próximos, o que não ficaria bonito.

CRIANDO O BOTÃO
a) Button(...) {} → Cria um botão interativo na tela.

b)onClick = { result = (1..6).random() } →
- Quando o botão for pressionado, um novo número aleatório entre 1 e 6 será escolhido e salvo na variável result.
- Isso faz com que a imagem do dado mude automaticamente.

c) Text(stringResource(R.string.roll)) →
- Adiciona um texto dentro do botão.
- R.string.roll está no arquivo strings.xml, garantindo suporte para múltiplos idiomas.
*/

// Define a interface do usuário
// Essa função é a que realmente mostra os elementos na tela e faz o dado mudar ao apertar o botão
@Composable // indica que a função está criando algo visual no app
// Criando uma função que será responsável por mostrar o dado e o botão na tela
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // Variável de estado para armazenar o número sorteado
    var result by remember { mutableStateOf(1) }

    // Escolhendo a imagem do dado
    // Determina qual imagem será exibida com base no número sorteado
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    // Criando a interface do usuário
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Exibindo a imagem do botão
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )

        // Criando um espaço entre a imagem e o botão
        Spacer(modifier = Modifier.height(16.dp))

        // Criando o botão
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(R.string.roll))
        }
    }
}
