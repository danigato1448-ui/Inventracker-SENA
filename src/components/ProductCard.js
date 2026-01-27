import React from 'react';

/**
 * COMPONENTE: ProductCard
 * DESCRIPCIÓN: Representación visual de cada juguete.
 * CUMPLE CON: HU08 (Resaltado visual de stock crítico).
 */
const ProductCard = ({ nombre, stock, precio, categoria, esCritico }) => {
  return (
    <div style={{
      border: esCritico ? '2px solid #e74c3c' : '1px solid #ddd',
      padding: '15px',
      margin: '10px',
      borderRadius: '8px',
      width: '220px',
      backgroundColor: esCritico ? '#fff5f5' : '#ffffff',
      boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
      transition: 'all 0.3s ease'
    }}>
      <h4 style={{ margin: '0 0 10px 0', color: '#2c3e50' }}>{nombre}</h4>
      <p style={{ fontSize: '0.9em', color: '#7f8c8d', margin: '5px 0' }}>
        <strong>Categoría:</strong> {categoria}
      </p>
      <p style={{ fontSize: '1.1em', fontWeight: 'bold', margin: '10px 0' }}>
        ${new Number(precio).toLocaleString()}
      </p>
      
      <div style={{
        padding: '8px',
        borderRadius: '4px',
        backgroundColor: esCritico ? '#fee2e2' : '#f0fdf4',
        color: esCritico ? '#991b1b' : '#166534',
        fontWeight: 'bold',
        textAlign: 'center'
      }}>
        Stock: {stock} {esCritico ? '⚠️' : '✅'}
      </div>
    </div>
  );
};

export default ProductCard;