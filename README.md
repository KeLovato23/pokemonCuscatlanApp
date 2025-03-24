# 📱 Pokédex Cuscatlán

Una aplicación de Pokédex hecha con **Jetpack Compose**, que permite explorar y buscar Pokémon, ver detalles y registrar un perfil personalizado como entrenador.

---

## 🚀 Características principales

- Registro de perfil de entrenador (con validación de edad y DUI)
- Lista de Pokémon con buscador por nombre o número
- Detalle completo de cada Pokémon (imagen, tipo, estadísticas)
- Tema visual personalizado
- UI moderna y responsiva

---

## 🧰 Tecnologías utilizadas

- ✅ Jetpack Compose
- ✅ Room (persistencia local)
- ✅ Retrofit (API REST)
- ✅ Coil (carga de imágenes)
- ✅ Navigation Compose
- ✅ ViewModel + StateFlow

---

## 📸 Pantallas

### 🟡 SplashScreen
Pantalla de bienvenida animada al iniciar la app.

📷 _Aquí irá una imagen de la pantalla_

---

### 🟢 Trainer Setup
Formulario para registrar perfil del entrenador Pokémon:  
Nombre, foto, pasatiempo, fecha de nacimiento y DUI si aplica.

📷 _Aquí irá una imagen de la pantalla_

---

### 🔵 MainScreen
Pantalla principal con barra superior, buscador y listado de Pokémon en grid.

📷 _Aquí irá una imagen de la pantalla_

---

### 🟣 DetailScreen
Detalle de cada Pokémon: nombre, número, tipo, altura, peso, descripción y estadísticas visuales.

📷 _Aquí irá una imagen de la pantalla_

---

### 🟠 Editar Perfil
Pantalla similar al registro para actualizar los datos del entrenador.

📷 _Aquí irá una imagen de la pantalla_

---

## 📦 Estructura del proyecto

```
📁 data/
   ├─ remote/         → Retrofit + API
   ├─ local/          → Room (Dao + Entity)
   └─ repository/     → Repositorio unificado

📁 domain/
   ├─ model/          → Modelos de negocio

📁 ui/
   ├─ screens/        → Splash, Main, Detail, Setup
   ├─ components/     → Reutilizables (SearchBar, TopBar, etc.)
   └─ theme/          → Colores, Tipografía

📁 utils/             → Validaciones, formatos y helpers

📁 navigation/        → NavGraph
```

---

## 📥 Instalación y ejecución

1. Clona el repositorio  
   `git clone https://github.com/tu_usuario/pokedex-cuscatlan.git`

2. Ábrelo en Android Studio (Hedgehog+ recomendado)

3. Ejecuta en un emulador o dispositivo físico

---

## 🧑‍💻 Autor

Desarrollado por **Kevin Lovato**  
📧 keving.20201997@gmail.com
