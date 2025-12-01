//use AlpesCabNoSQL

db.createCollection("clientes", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["_id", "nombre", "correo", "celular", "cedula"],
      properties: {
        _id: { bsonType: "string" },
        nombre: { bsonType: "string" },
        correo: { bsonType: "string" },
        celular: { bsonType: "string" },
        cedula: { bsonType: "string" },
        numeroTarjeta: { bsonType: "string" },
        nombreTitularTarjeta: { bsonType: "string" },
        fechaVencimientoTarjeta: { bsonType: "string" },
        cvcTarjeta: { bsonType: "string" }
      }
    }
  }
})

db.createCollection("conductores", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["_id", "nombre", "correo", "celular"],
      properties: {
        _id: { bsonType: "string" },
        nombre: { bsonType: "string" },
        correo: { bsonType: "string" },
        celular: { bsonType: "string" }
      }
    }
  }
})

db.createCollection("ciudades", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["_id", "nombre"],
      properties: {
        _id: { bsonType: "string" },
        nombre: { bsonType: "string" }
      }
    }
  }
})

db.createCollection("vehiculos", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["_id", "marca", "modelo", "capacidad", "tipoVehiculo", "nivelTransporte", "idConductor"],
      properties: {
        _id: { bsonType: "string" },
        marca: { bsonType: "string" },
        modelo: { bsonType: "string" },
        capacidad: { bsonType: "int" },
        tipoVehiculo: {
          bsonType: "string",
          enum: ["CARRO", "MOTO", "CAMIONETA"]
        },
        nivelTransporte: {
          bsonType: "string",
          enum: ["ESTANDAR", "CONFORT", "LARGE"]
        },
        idConductor: { bsonType: "string" },
        disponibilidades: {
          bsonType: "array",
          items: {
            bsonType: "object",
            required: ["dia", "horaInicio", "horaFin", "tipoServicio"],
            properties: {
              dia: {
                bsonType: "string",
                enum: ["LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO"]
              },
              horaInicio: { bsonType: "string" },
              horaFin: { bsonType: "string" },
              tipoServicio: {
                bsonType: "string",
                enum: ["PASAJEROS", "COMIDA", "MERCANCIAS"]
              }
            }
          }
        }
      }
    }
  }
})

db.createCollection("tarifas", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["_id", "tipoServicio", "nivelTransporte", "costoBase", "costoPorKm"],
      properties: {
        _id: { bsonType: "string" },
        tipoServicio: {
          bsonType: "string",
          enum: ["PASAJEROS", "COMIDA", "MERCANCIAS"]
        },
        nivelTransporte: {
          bsonType: "string",
          enum: ["ESTANDAR", "CONFORT", "LARGE"]
        },
        costoBase: { bsonType: ["double", "int", "decimal"] },
        costoPorKm: { bsonType: ["double", "int", "decimal"] }
      }
    }
  }
})

db.createCollection("servicios", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["_id","idCliente","idConductor","placaVehiculo","tipoServicio","nivelTransporte","ruta","fechaSolicitud","distanciaKm","costoTotal","estado"],
      properties: {
        _id: { bsonType: "string" },
        idCliente: { bsonType: "string" },
        idConductor: { bsonType: "string" },
        placaVehiculo: { bsonType: "string" },
        tipoServicio: {
          bsonType: "string",
          enum: ["PASAJEROS", "COMIDA", "MERCANCIAS"]
        },
        nivelTransporte: {
          bsonType: "string",
          enum: ["ESTANDAR", "CONFORT", "LARGE"]
        },
        ruta: {
          bsonType: "object",
          required: ["origen", "destino", "distanciaKm"],
          properties: {
            origen: {
              bsonType: "object",
              required: ["direccion","latitud","longitud","idCiudad"],
              properties: {
                direccion: { bsonType: "string" },
                latitud: { bsonType: ["double","int","decimal"] },
                longitud: { bsonType: ["double","int","decimal"] },
                idCiudad: { bsonType: "string" }
              }
            },
            destino: {
              bsonType: "object",
              required: ["direccion","latitud","longitud","idCiudad"],
              properties: {
                direccion: { bsonType: "string" },
                latitud: { bsonType: ["double","int","decimal"] },
                longitud: { bsonType: ["double","int","decimal"] },
                idCiudad: { bsonType: "string" }
              }
            },
            paradas: {
              bsonType: "array",
              items: {
                bsonType: "object",
                required: ["direccion","latitud","longitud","idCiudad"],
                properties: {
                  direccion: { bsonType: "string" },
                  latitud: { bsonType: ["double","int","decimal"] },
                  longitud: { bsonType: ["double","int","decimal"] },
                  idCiudad: { bsonType: "string" }
                }
              }
            },
            distanciaKm: { bsonType: ["double","int","decimal"] }
          }
        },
        fechaSolicitud: { bsonType: "date" },
        horaInicio: { bsonType: ["date", "null"] },
        horaFin: { bsonType: ["date", "null"] },
        costoTotal: { bsonType: ["double","int","decimal"] },
        distanciaKm: { bsonType: ["double","int","decimal"] },
        estado: {
          bsonType: "string",
          enum: ["EN_CURSO","FINALIZADO"]
        }
      }
    }
  }
})

db.createCollection("calificaciones", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["_id","idServicio","idCliente","idConductor","valor","fecha"],
      properties: {
        _id: { bsonType: "string" },
        idServicio: { bsonType: "string" },
        idCliente: { bsonType: "string" },
        idConductor: { bsonType: "string" },
        valor: { bsonType: "int" },
        comentario: { bsonType: "string" },
        fecha: { bsonType: "date" }
      }
    }
  }
})