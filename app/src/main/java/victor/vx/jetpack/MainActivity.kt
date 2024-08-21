package victor.vx.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import victor.vx.jetpack.ui.theme.JetpackTheme
import victor.vx.jetpack.ui.theme.fontExo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackTheme {
                Surface {
                    LoginPage()
                }
            }
        }
    }
}

@Composable
fun TextEnter() {
    Text(
        text = "Entre aqui",
        fontFamily = fontExo,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        color = Color.Blue,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun TextWelcome() {
    Text(
        text = "Bem-vindo de volta, você fez falta!",
        style = MaterialTheme.typography.bodyMedium,
        fontSize = 16.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 30.dp)
    )
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    isPasswordVisible: MutableState<Boolean> = mutableStateOf(false)
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Image(
                imageVector = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color(0x171F41BB),
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        label = {
            Text(
                text = labelText,
                style = TextStyle(
                    color = Color.Black
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = isPassword,
            imeAction = if (isPassword) ImeAction.Done else ImeAction.Next
        ),
        singleLine = true,
        visualTransformation = if (isPassword && !isPasswordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
                    val visibilityOn = painterResource(id = R.drawable.ic_visibility)
                    val visibilityOff = painterResource(id = R.drawable.ic_visibility_off)

                    Icon(
                        painter = if (isPasswordVisible.value) visibilityOff else visibilityOn,
                        contentDescription = if (isPasswordVisible.value) "Hide password" else "Show password",
                        modifier = Modifier.size(26.dp),
                        tint = Color.Black
                    )
                }
            }
        },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    )
}

@Composable
fun TextForgotPassword(modifier: Modifier = Modifier) {
    Text(
        text = "Esqueceu a senha?",
        modifier = modifier,
        style = TextStyle(color = Color.Blue, fontSize = 14.sp)
    )
}

@Composable
fun LoginBtn(
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Conecte-se",
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginPage() {
    var email by remember { mutableStateOf<String>("") }
    var password by remember { mutableStateOf("") }
    val isPasswordVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp))
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextEnter()

            TextWelcome()

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                labelText = "Email",
                icon = Icons.Default.Email
            )

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                labelText = "Password",
                icon = Icons.Default.Lock,
                isPassword = true,
                isPasswordVisible = isPasswordVisible
            )

            TextForgotPassword(
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LoginBtn(onClick = {  })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage()
}
