import { useState, useEffect } from "react"
import { Link } from "react-router-dom"
import { puntos_finales } from "../services/api"
import { alertaRedireccion } from "../helpers/alertas"
import Header from "../components/Encabezado"
import Footer from "../components/PiePagina"
import imagenFondo from "../assets/images/marca.png"

function Acceso() {
  const [nombreUsuario, setNombreUsuario] = useState("")
  const [contrasena, setContrasena] = useState("")
  const [usuarios, setUsuarios] = useState([])

  function obtenerUsuarios() {
    fetch(puntos_finales.usuarios)
      .then((res) => res.json())
      .then((datos) => setUsuarios(datos))
      .catch((err) => console.log(err))
  }

  useEffect(() => { obtenerUsuarios() }, [])

  function encontrarUsuario() {
    return usuarios.find(
      (item) => nombreUsuario === item.username && contrasena === item.password
    )
  }

  function iniciarSesion(e) {
    e.preventDefault()
    if (!nombreUsuario || !contrasena)
      return alertaRedireccion("Campos vacíos", "Completa usuario y contraseña", "/acceso", "warning")
    if (encontrarUsuario()) {
      localStorage.setItem("usuario", JSON.stringify(encontrarUsuario()))
      return alertaRedireccion("Bienvenido", "Serás redireccionado al panel", "/panel", "success")
    }
    return alertaRedireccion("Error", "Usuario o contraseña incorrectos", "/acceso", "error")
  }

  return (
    <div className="app">
      <Header />

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
            <button type="submit" className="login-btn">Acceder</button>
          </form>
        </div>
      </main>

      <Footer />
    </div>
  )
}

export default Acceso