import React from 'react';
import Inventario from './components/inventario';

function App() {
  return (
    <div className="App">
      <header style={{ backgroundColor: '#2c3e50', padding: '20px', color: 'white', textAlign: 'center' }}>
        <h1>Sistema Inventracker - SENA</h1>
      </header>
      <main>
        <Inventario />
      </main>
    </div>
  );
}

export default App;
