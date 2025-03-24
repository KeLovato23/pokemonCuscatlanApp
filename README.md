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

<img src="https://github.com/user-attachments/assets/471d3c56-50a8-469c-9183-628acccd7215" alt="SplashScreen" width="300"/>

---

### 🟢 Trainer Setup
Formulario para registrar perfil del entrenador Pokémon:  
Nombre, foto, pasatiempo, fecha de nacimiento y DUI si aplica.

<img src="https://github.com/user-attachments/assets/60f8599c-1792-43a4-98fb-55d2f4c082ab" alt="Trainer Setup" width="300"/>

---

### 🔵 MainScreen
Pantalla principal con barra superior, buscador y listado de Pokémon en grid.

<img src="https://github.com/user-attachments/assets/dedab99e-62b7-4b9e-a109-9e512897a7be" alt="Main Screen" width="300"/>

---

### 🟣 DetailScreen
Detalle de cada Pokémon: nombre, número, tipo, altura, peso, descripción y estadísticas visuales.

<img src="https://github.com/user-attachments/assets/d1c3d50b-300e-4444-9569-663b3ff64385" alt="Detail Screen 1" width="300"/>
<img src="https://github.com/user-attachments/assets/ebe97cb4-9279-4e67-9a31-09725468262a" alt="Detail Screen 2" width="300"/>

---

### 🟠 Editar Perfil
Pantalla similar al registro para actualizar los datos del entrenador.

<img src="https://github.com/user-attachments/assets/a275e303-9222-458b-8dc3-5834f781d863" alt="Editar Perfil" width="300"/>

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
