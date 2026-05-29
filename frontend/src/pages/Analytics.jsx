import { useEffect, useState } from "react"
import Encabezado from "../components/Encabezado"
import PiePagina from "../components/PiePagina"
import { puntos_finales } from "../services/api"

function Analytics() {
  const [proyectos, setProyectos] = useState([])
  const [estudiantes, setEstudiantes] = useState([])
  const [error, setError] = useState(false)

  useEffect(() => {
    cargarDatos()
  }, [])

  const cargarDatos = async () => {
    try {
      const resProyectos = await fetch(puntos_finales.proyectos)
      const resEstudiantes = await fetch(puntos_finales.estudiantes)

      const dataProyectos = await resProyectos.json()
      const dataEstudiantes = await resEstudiantes.json()

      setProyectos(dataProyectos)
      setEstudiantes(dataEstudiantes)
    } catch (error) {
      console.log("Error al cargar analítica:", error)
      setError(true)
    }
  }

  const proyectosPorCategoria = proyectos.reduce((acc, proyecto) => {
    const categoria = proyecto.categoria || "Sin categoría"
    acc[categoria] = (acc[categoria] || 0) + 1
    return acc
  }, {})

  return (
    <div className="app">
      <Encabezado />

      <main className="analytics-container">
        <h1>Analítica del Repositorio</h1>
        <p>
          Este módulo permite visualizar indicadores generales de los proyectos
          académicos registrados en StudentsRepo.
        </p>

        {error && (
          <div className="analytics-alerta">
            No se pudo conectar con el backend. Los indicadores se mostrarán
            cuando el servicio de Render esté activo.
          </div>
        )}

        <section className="analytics-cards">
          <div className="analytics-card">
            <h2>{proyectos.length}</h2>
            <p>Total de proyectos</p>
          </div>

          <div className="analytics-card">
            <h2>{estudiantes.length}</h2>
            <p>Total de estudiantes</p>
          </div>

          <div className="analytics-card">
            <h2>{Object.keys(proyectosPorCategoria).length}</h2>
            <p>Categorías registradas</p>
          </div>
        </section>

        <section className="analytics-section">
          <h2>Proyectos por categoría</h2>

          {Object.keys(proyectosPorCategoria).length === 0 ? (
            <p>No hay datos disponibles por el momento.</p>
          ) : (
            Object.entries(proyectosPorCategoria).map(([categoria, total]) => (
              <div className="analytics-barra" key={categoria}>
                <span>{categoria}</span>
                <strong>{total}</strong>
              </div>
            ))
          )}
        </section>

        <section className="analytics-section">
          <h2>Últimos proyectos registrados</h2>

          {proyectos.length === 0 ? (
            <p>No hay proyectos disponibles por el momento.</p>
          ) : (
            <ul>
              {proyectos.slice(0, 5).map((proyecto) => (
                <li key={proyecto.id}>
                  {proyecto.titulo || proyecto.nombre || "Proyecto sin nombre"}
                </li>
              ))}
            </ul>
          )}
        </section>
      </main>

      <PiePagina />
    </div>
  )
}

export default Analytics