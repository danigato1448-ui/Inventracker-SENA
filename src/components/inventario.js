import React, { useState } from 'react';
import ProductCard from './ProductCard';

/**
 * COMPONENTE: Inventario
 * DESCRIPCIÓN: Gestiona el flujo de productos, búsqueda y alertas de stock bajo.
 * CUMPLE CON: HU03 (Agregar), HU04 (Consultar) y HU08 (Alertas)[cite: 3].
 */
const Inventario = () => {
  // Estado inicial basado en el Diagrama de Clases (Atributos: nombre, stock, precio) 
  const [productos, setProductos] = useState([
    { id: 1, nombre: "LEGO Star Wars", stock: 3, precio: 120000, categoria: "Educativos" },
    { id: 2, nombre: "Barbie Dreamhouse", stock: 10, precio: 250000, categoria: "Muñecas" }
  ]);

  // Estado para el formulario de nuevo producto (HU03) [cite: 3]
  const [nuevoProducto, setNuevoProducto] = useState({ nombre: '', stock: '', precio: '', categoria: '' });
  const [busqueda, setBusqueda] = useState("");

  // Función para agregar producto al inventario (HU03) [cite: 3]
  const agregarProducto = (e) => {
    e.preventDefault();
    if (!nuevoProducto.nombre || !nuevoProducto.stock) return alert("Complete los campos obligatorios");
    
    const item = { ...nuevoProducto, id: Date.now(), stock: parseInt(nuevoProducto.stock) };
    setProductos([...productos, item]);
    setNuevoProducto({ nombre: '', stock: '', precio: '', categoria: '' }); // Limpiar formulario
  };

  // Lógica de filtrado para búsqueda rápida (HU04) [cite: 3]
  const productosFiltrados = productos.filter(p => 
    p.nombre.toLowerCase().includes(busqueda.toLowerCase())
  );

  return (
    <div style={{ padding: '20px' }}>
      {/* SECCIÓN DE ALERTAS (HU08) [cite: 3] */}
      {productos.some(p => p.stock < 5) && (
        <div style={{ background: '#ffcccc', padding: '10px', marginBottom: '20px', borderRadius: '5px' }}>
          <strong>⚠️ Alerta de Stock Mínimo:</strong> Revise los productos resaltados en rojo[cite: 3].
        </div>
      )}

      {/* FORMULARIO DE REGISTRO (HU03) [cite: 3] */}
      <form onSubmit={agregarProducto} style={{ marginBottom: '30px', padding: '15px', border: '1px solid #ddd' }}>
        <h3>Registrar Nuevo Juguete</h3>
        <input type="text" placeholder="Nombre" value={nuevoProducto.nombre} onChange={e => setNuevoProducto({...nuevoProducto, nombre: e.target.value})} />
        <input type="number" placeholder="Stock" value={nuevoProducto.stock} onChange={e => setNuevoProducto({...nuevoProducto, stock: e.target.value})} />
        <input type="number" placeholder="Precio" value={nuevoProducto.precio} onChange={e => setNuevoProducto({...nuevoProducto, precio: e.target.value})} />
        <button type="submit">Agregar al Sistema</button>
      </form>

      {/* BUSCADOR (HU04) [cite: 3] */}
      <input 
        type="text" 
        placeholder="Buscar juguete por nombre..." 
        onChange={(e) => setBusqueda(e.target.value)}
        style={{ width: '100%', padding: '10px', marginBottom: '20px' }}
      />

      {/* LISTADO DE PRODUCTOS */}
      <div style={{ display: 'flex', flexWrap: 'wrap' }}>
        {productosFiltrados.map(p => (
          <ProductCard key={p.id} {...p} />
        ))}
      </div>
    </div>
  );
};

export default Inventario;