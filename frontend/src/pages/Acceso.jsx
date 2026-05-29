import { useState } from "react"
import { Link } from "react-router-dom"
import { alertaRedireccion } from "../helpers/alertas"
import Encabezado from "../components/Encabezado"
import PiePagina from "../components/PiePagina"
import imagenFondo from "../assets/images/marca.png"

function Acceso() {
  const [nombreUsuario, setNombreUsuario] = useState("")
  const [contrasena, setContrasena] = useState("")
  const [cargando, setCargando] = useState(false)

  const iniciarSesion = async (e) => {
    e.preventDefault()
    if (!nombreUsuario || !contrasena) {
      alertaRedireccion("Campos vacíos", "Completa usuario y contraseña", "/acceso", "warning")
      return
    }

    setCargando(true)

    try {
      // Buscar en admins
      const resAdmins = await fetch("https://studentsrepo-backend.onrender.com/api/admins")
      const admins = await resAdmins.json()
      const adminEncontrado = admins.find(
        a => a.username === nombreUsuario && a.password === contrasena
      )

      if (adminEncontrado) {
        localStorage.setItem("usuario", JSON.stringify({ ...adminEncontrado, role: "admin" }))
        alertaRedireccion("Bienvenido", `Hola ${adminEncontrado.nombre || adminEncontrado.username}`, "/panel", "success")
        return
      }

      // Buscar en estudiantes
      const resEstudiantes = await fetch("https://studentsrepo-backend.onrender.com/api/estudiantes")
      const estudiantes = await resEstudiantes.json()
      const estudianteEncontrado = estudiantes.find(
        e => e.username === nombreUsuario && e.password === contrasena
      )

      if (estudianteEncontrado) {
        localStorage.setItem("usuario", JSON.stringify({ ...estudianteEncontrado, role: "estudiante" }))
        alertaRedireccion("Bienvenido", `Hola ${estudianteEncontrado.nombre || estudianteEncontrado.username}`, "/panel", "success")
        return
      }

      alertaRedireccion("Error", "Usuario o contraseña incorrectos", "/acceso", "error")

    } catch (error) {
      console.error("Error real del login:", error)
      alertaRedireccion("Error", "No se pudo conectar con el servidor", "/acceso", "error")
    } finally {
      setCargando(false)
    }
  }

  return (
    <div className="app">
      <Encabezado />
      <main className="form-login-container" style={{
        backgroundImage: `url(${imagenFondo})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
        flex: 1
      }}>
        <div className="login-card">
          <h2 className="login-titulo">Iniciar sesión</h2>
          <form className="form" onSubmit={iniciarSesion}>
            <input
              type="text"
              placeholder="Usuario"
              onChange={(e) => setNombreUsuario(e.target.value)}
            />
            <input
              type="password"
              placeholder="Contraseña"
              onChange={(e) => setContrasena(e.target.value)}
            />
            <button type="submit" className="login-btn" disabled={cargando}>
              {cargando ? "Verificando..." : "Acceder"}
            </button>
          </form>
        </div>
      </main>
      <PiePagina />
    </div>
  )
}

export default Acceso