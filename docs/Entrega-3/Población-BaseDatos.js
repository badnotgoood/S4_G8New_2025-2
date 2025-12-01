
// use AlpesCabNoSQL 

db.clientes.insertMany([
  {
    _id: "CLI001",
    nombre: "Mario Laserna",
    correo: "mario.laserna@uniandes.edu.co",
    celular: "3001234567",
    cedula: "1234567890",
    numeroTarjeta: "4111111111111111",
    nombreTitularTarjeta: "MARIO LASERNA",
    fechaVencimientoTarjeta: "12/28",
    cvcTarjeta: "123"
  },
  {
    _id: "CLI002",
    nombre: "Ana Gómez",
    correo: "ana.gomez@correo.com",
    celular: "3012223344",
    cedula: "9876543210",
    numeroTarjeta: "5555444433332222",
    nombreTitularTarjeta: "ANA GOMEZ",
    fechaVencimientoTarjeta: "11/27",
    cvcTarjeta: "456"
  },
  {
    _id: "CLI003",
    nombre: "Carlos Pérez",
    correo: "carlos.perez@correo.com",
    celular: "3021112233",
    cedula: "1112223334"
  },
  {
    _id: "CLI004",
    nombre: "Laura Rodríguez",
    correo: "laura.rodriguez@correo.com",
    celular: "3039998877",
    cedula: "2223334445"
  },
  {
    _id: "CLI005",
    nombre: "Sofía Martínez",
    correo: "sofia.martinez@correo.com",
    celular: "3045556677",
    cedula: "3334445556"
  }
]);

db.conductores.insertMany([
  {
    _id: "COND1",
    nombre: "Juan López",
    correo: "juan.lopez@correo.com",
    celular: "3101112233"
  },
  {
    _id: "COND2",
    nombre: "Marta Díaz",
    correo: "marta.diaz@correo.com",
    celular: "3102223344"
  },
  {
    _id: "COND3",
    nombre: "Pedro Ruiz",
    correo: "pedro.ruiz@correo.com",
    celular: "3103334455"
  },
  {
    _id: "COND4",
    nombre: "Lucía Herrera",
    correo: "lucia.herrera@correo.com",
    celular: "3104445566"
  },
  {
    _id: "COND5",
    nombre: "Andrés Silva",
    correo: "andres.silva@correo.com",
    celular: "3105556677"
  }
]);

db.ciudades.insertMany([
  { _id: "BOG", nombre: "Bogotá" },
  { _id: "MED", nombre: "Medellín" },
  { _id: "CAL", nombre: "Cali" },
  { _id: "BAR", nombre: "Barranquilla" },
  { _id: "CAR", nombre: "Cartagena" }
]);

db.vehiculos.insertMany([
  {
    _id: "ABC123",
    marca: "Toyota",
    modelo: "Corolla",
    capacidad: 4,
    tipoVehiculo: "CARRO",
    nivelTransporte: "ESTANDAR",
    idConductor: "COND1",
    disponibilidades: [
      {
        dia: "LUNES",
        horaInicio: "08:00",
        horaFin: "12:00",
        tipoServicio: "PASAJEROS"
      },
      {
        dia: "MARTES",
        horaInicio: "14:00",
        horaFin: "18:00",
        tipoServicio: "PASAJEROS"
      }
    ]
  },
  {
    _id: "BCD234",
    marca: "Kia",
    modelo: "Rio",
    capacidad: 4,
    tipoVehiculo: "CARRO",
    nivelTransporte: "CONFORT",
    idConductor: "COND2",
    disponibilidades: [
      {
        dia: "LUNES",
        horaInicio: "18:00",
        horaFin: "23:00",
        tipoServicio: "PASAJEROS"
      }
    ]
  },
  {
    _id: "CDE345",
    marca: "Chevrolet",
    modelo: "Spark",
    capacidad: 4,
    tipoVehiculo: "CARRO",
    nivelTransporte: "ESTANDAR",
    idConductor: "COND3",
    disponibilidades: [
      {
        dia: "MIERCOLES",
        horaInicio: "07:00",
        horaFin: "10:00",
        tipoServicio: "COMIDA"
      }
    ]
  },
  {
    _id: "DDD111",
    marca: "Yamaha",
    modelo: "FZ25",
    capacidad: 1,
    tipoVehiculo: "MOTO",
    nivelTransporte: "ESTANDAR",
    idConductor: "COND4",
    disponibilidades: [
      {
        dia: "JUEVES",
        horaInicio: "09:00",
        horaFin: "17:00",
        tipoServicio: "COMIDA"
      }
    ]
  },
  {
    _id: "EEE222",
    marca: "Renault",
    modelo: "Kangoo",
    capacidad: 2,
    tipoVehiculo: "CAMIONETA",
    nivelTransporte: "LARGE",
    idConductor: "COND5",
    disponibilidades: [
      {
        dia: "VIERNES",
        horaInicio: "08:00",
        horaFin: "18:00",
        tipoServicio: "MERCANCIAS"
      }
    ]
  }
]);

db.tarifas.insertMany([
  {
    _id: "TAR_PAS_EST",
    tipoServicio: "PASAJEROS",
    nivelTransporte: "ESTANDAR",
    costoBase: 8000,
    costoPorKm: 1200
  },
  {
    _id: "TAR_PAS_CONF",
    tipoServicio: "PASAJEROS",
    nivelTransporte: "CONFORT",
    costoBase: 10000,
    costoPorKm: 1500
  },
  {
    _id: "TAR_COM_EST",
    tipoServicio: "COMIDA",
    nivelTransporte: "ESTANDAR",
    costoBase: 6000,
    costoPorKm: 1000
  },
  {
    _id: "TAR_MER_LAR",
    tipoServicio: "MERCANCIAS",
    nivelTransporte: "LARGE",
    costoBase: 15000,
    costoPorKm: 2000
  }
]);

db.servicios.insertMany([
  {
    _id: "SRV001",
    idCliente: "CLI001",
    idConductor: "COND1",
    placaVehiculo: "ABC123",
    tipoServicio: "PASAJEROS",
    nivelTransporte: "ESTANDAR",
    ruta: {
      origen: {
        direccion: "Calle 10 # 5-30",
        latitud: 4.6001,
        longitud: -74.0721,
        idCiudad: "BOG"
      },
      destino: {
        direccion: "Carrera 15 # 93-60",
        latitud: 4.6765,
        longitud: -74.0489,
        idCiudad: "BOG"
      },
      paradas: [],
      distanciaKm: 12.5
    },
    fechaSolicitud: ISODate("2025-11-30T23:31:47Z"),
    horaInicio: null,
    horaFin: null,
    costoTotal: 20000,
    distanciaKm: 12.5,
    estado: "EN_CURSO"
  },
  {
    _id: "SRV002",
    idCliente: "CLI002",
    idConductor: "COND2",
    placaVehiculo: "BCD234",
    tipoServicio: "PASAJEROS",
    nivelTransporte: "CONFORT",
    ruta: {
      origen: {
        direccion: "Cra 7 # 72-20",
        latitud: 4.6520,
        longitud: -74.0580,
        idCiudad: "BOG"
      },
      destino: {
        direccion: "Calle 116 # 7-15",
        latitud: 4.6941,
        longitud: -74.0302,
        idCiudad: "BOG"
      },
      paradas: [],
      distanciaKm: 8.0
    },
    fechaSolicitud: ISODate("2025-12-01T08:00:00Z"),
    horaInicio: ISODate("2025-12-01T08:05:00Z"),
    horaFin: ISODate("2025-12-01T08:30:00Z"),
    costoTotal: 19000,
    distanciaKm: 8.0,
    estado: "FINALIZADO"
  },
  {
    _id: "SRV003",
    idCliente: "CLI003",
    idConductor: "COND4",
    placaVehiculo: "DDD111",
    tipoServicio: "COMIDA",
    nivelTransporte: "ESTANDAR",
    ruta: {
      origen: {
        direccion: "Restaurante Calle 45 # 20-10",
        latitud: 4.6400,
        longitud: -74.0800,
        idCiudad: "BOG"
      },
      destino: {
        direccion: "Carrera 30 # 50-20",
        latitud: 4.6405,
        longitud: -74.0850,
        idCiudad: "BOG"
      },
      paradas: [],
      distanciaKm: 3.5
    },
    fechaSolicitud: ISODate("2025-12-01T12:15:00Z"),
    horaInicio: ISODate("2025-12-01T12:18:00Z"),
    horaFin: ISODate("2025-12-01T12:35:00Z"),
    costoTotal: 9000,
    distanciaKm: 3.5,
    estado: "FINALIZADO"
  },
  {
    _id: "SRV004",
    idCliente: "CLI004",
    idConductor: "COND5",
    placaVehiculo: "EEE222",
    tipoServicio: "MERCANCIAS",
    nivelTransporte: "LARGE",
    ruta: {
      origen: {
        direccion: "Bodega Zona Industrial",
        latitud: 4.6310,
        longitud: -74.1200,
        idCiudad: "BOG"
      },
      destino: {
        direccion: "Centro Comercial Gran Plaza",
        latitud: 4.6200,
        longitud: -74.1100,
        idCiudad: "BOG"
      },
      paradas: [],
      distanciaKm: 6.0
    },
    fechaSolicitud: ISODate("2025-12-01T09:00:00Z"),
    horaInicio: ISODate("2025-12-01T09:10:00Z"),
    horaFin: ISODate("2025-12-01T09:45:00Z"),
    costoTotal: 26000,
    distanciaKm: 6.0,
    estado: "FINALIZADO"
  }
]);

db.calificaciones.insertMany([
  {
    _id: "CAL001",
    idServicio: "SRV002",
    idCliente: "CLI002",
    idConductor: "COND2",
    valor: 5,
    comentario: "Viaje muy cómodo y puntual.",
    fecha: ISODate("2025-12-01T09:00:00Z")
  },
  {
    _id: "CAL002",
    idServicio: "SRV003",
    idCliente: "CLI003",
    idConductor: "COND4",
    valor: 4,
    comentario: "Entrega rápida, la comida llegó caliente.",
    fecha: ISODate("2025-12-01T13:00:00Z")
  },
  {
    _id: "CAL003",
    idServicio: "SRV004",
    idCliente: "CLI004",
    idConductor: "COND5",
    valor: 3,
    comentario: "Buen servicio, pero algo de tráfico.",
    fecha: ISODate("2025-12-01T10:30:00Z")
  }
]);