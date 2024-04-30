# melisearch

## Descripción

Melisearch es una aplicación Android que te permite buscar productos en una plataforma externa utilizando Retrofit para simplificar las llamadas a la API y Coroutines para una gestión asíncrona eficiente. Con una arquitectura basada en MVP (Modelo-Vista-Presentador), la aplicación separa claramente la lógica de negocio de la interfaz de usuario, facilitando el mantenimiento y la prueba del código.

### Componentes Principales

#### MainContract:
Define la interfaz entre la vista y el presentador, especificando métodos para mostrar productos y errores.

#### RetrofitService:
Interfaz para las llamadas de Retrofit, con un método suspendido para listar productos basado en una búsqueda.

#### RetrofitServiceFactory:
Crea la instancia de Retrofit con la URL base y el convertidor Gson.

#### MainPresenter:
Implementa la lógica de búsqueda de productos, maneja la coroutina y actualiza la vista con los resultados o errores.

#### MainActivity:
Configura la UI, inicializa el presentador y maneja la interacción del usuario con el botón de búsqueda.

#### ProductAdapter:
Adaptador para RecyclerView que maneja la visualización de productos.

### Tecnologías Utilizadas

- **Retrofit:** Simplificación de API y conversión de JSON para gestionar las llamadas a la API web.
- **Coroutines:** Facilita la escritura de código asíncrono limpio y mejora el rendimiento al realizar operaciones en segundo plano.
- **Glide:** Eficiente para cargar y cachear imágenes, mejorando la experiencia del usuario.
- **JUnit Jupiter:** Framework estándar para realizar pruebas unitarias en aplicaciones Java y Kotlin.
- **Mockito Kotlin:** Permite crear objetos simulados (mocks) para aislar y probar componentes específicos del código.

### Guía de Uso

#### Estructura General

- **MainContract:** Define la interfaz entre la vista y el presentador.
- **RetrofitService:** Gestiona la comunicación con la API externa.
- **RetrofitServiceFactory:** Crea una instancia de RetrofitService configurando la URL base y el convertidor Gson.
- **MainPresenter:** Implementa MainContract.Presenter, maneja la lógica de negocio, y utiliza RetrofitService para obtener los datos y actualizar la vista.
- **MainActivity:** Actúa como la vista en MVP, maneja la interacción del usuario, inicializa el presentador y actualiza la UI con los datos recibidos.
- **ProductAdapter:** Adaptador para RecyclerView, maneja la visualización de productos utilizando Glide para cargar imágenes.

#### Flujo de la Aplicación

- **MainActivity:** Vista principal donde se inicializa MainPresenter.
- **Interacción de Usuario:** El usuario ingresa su consulta y realiza la búsqueda.
- **MainPresenter:** Se encarga de la lógica de búsqueda de productos y actualiza la vista con los resultados.
- **Mostrar Resultados:** Los productos encontrados se muestran en la interfaz de usuario.

#### Comunicación con la API

- **RetrofitServiceFactory:** Crea una instancia de RetrofitService usando Retrofit y Gson para consumir el endpoint.
- **MainPresenter:** Implementa la lógica de búsqueda de productos utilizando RetrofitService.

#### Interfaz de Usuario

- **RecyclerView y Adapter:** Muestra los productos en una lista en dos columnas.
- **Actualización de UI:** Actualiza la lista de productos en la interfaz de usuario.
- **Interacción del Usuario:** Permite buscar productos utilizando un campo de texto y un botón de búsqueda.
- **Manejo de Errores:** Muestra mensajes de error en caso de fallos durante la búsqueda.


### android:configChanges="orientation|screenSize"

Cuando utilizas android:configChanges="orientation|screenSize" en tu archivo de manifiesto de Android,
estás indicando que manejarás tú mismo los cambios de configuración de orientación y tamaño de pantalla.
Esto significa que no necesitas guardar el estado de la actividad manualmente porque el sistema no destruirá
y recreará la actividad cuando cambien estas configuraciones. Sin embargo, si tienes otros datos o estados 
que deseas conservar a través de los cambios de configuración, deberás guardarlos y restaurarlos adecuadamente.

### Licencia

Este proyecto está bajo la Licencia MIT.