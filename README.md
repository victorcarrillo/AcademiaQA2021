# Academia QA 2021

##Requerimientos

###Chrome
Chrome puede ser descargado e instalado desde la [página oficial](https://www.google.com/intl/es-419/chrome/)

###Maven
Se requiere el archivo [apache-maven-3.8.1-bin.zip](https://downloads.apache.org/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.zip).

###Java
La versión correcta de Java depende del ambiente en el que el programa será ejecutado. Las versiones se pueden ver en [la página oficial de Java](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html).


##Variables de ambiente
Maven y Java requiren que se agregen ciertos parámetros a las variables de ambiente del sistema.

1. Abre "Panel de Control"
2. "Sistema"
3. "Configuación avanzada de sistema"
5. Cambia a la pestaña "Avanzado"
6. "Variables de Ambiente"
7. Bajo "Variables de sistema" (para todos los usuarios), selecciona "Path"
8. "Edit"
9. (Para Windows 10) Aparece una tabla que muestra los directorios incluidos en el PATH actual ⇒ "New" ⇒ "Browse..." para seleccionar el nuevo directoria a agregar a  PATH (Es mejor copiar y pegar para evitar errores) ⇒ Click "Move Up" hasta que se encuentre en la parte superior ⇒ "OK" (No "Cancel") ⇒ "OK" ⇒ "OK".
10. (Para Windows más antiguos) Si no aparece la tabla, es tiempo de cambiar de computadora.

Utilizando los pasos anteriores, se deben agregar las variables
```aidl
%JAVA_HOME%/bin
```
y
```aidl
%MAVEN_HOME%/bin
```
al PATH general del sistema.

En caso de que la variable MAVEN_HOME no exista entre las variables de ambiente, será necesario agregarla manualmente, con el directorio en el que se encuentre el archivo descargado en los requerimientos.

##Ejecución de suites de pruebas
En el directorio raíz en el que se encuentra el proyecto, se puede ejecutar un comando para iniciar las pruebas.

```aidl
mvn test -D suiteXmlFile=testSuiteSwagLabs.xml
```

Con el comando anterior, se ejecutan todas las pruebas incluidas en la suite. En caso de que se deseé ejecutar solo un módulo de las pruebas, existen distintos archivos XML que contienen pruebas de cada uno de ellos.

El nombre del archivo *testSuiteSwagLabs.xml*, puede ser reemplazado por:

Nombre de archivo | Módulo a probar
------------ | -------------
loginTestSuite.xml | Inicios de sesión
inventoryTestSuite.xml | Catálogo de productos
productDetailTestSuite.xml | Vista de detalle de producto 
shoppingCartTestSuite.xml | Carrito de compra
purchaseFlowTestSuite.xml | Proceso de compra