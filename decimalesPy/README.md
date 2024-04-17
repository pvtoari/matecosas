
<h1 align="center">
<i>Pi de Gauss-Legendre</i>
  
<br>

$$\pi \approx \frac{(a_{n}+b_{n})^2}{4t_{n}}$$

</h1>

---
<p align="center"> Implementación de <a href="https://es.wikipedia.org/wiki/Algoritmo_de_Gauss-Legendre"> Pi de Gauss-Legendre</a>
en Python 3
</p>

## 🧐 Funcionamiento <a name = "about"></a>

Programa que mediante el uso del algoritmo Gauss-Legendre permite calcular decimales de pi con un error muy reducido en comparación a si se usasen otros como Leibniz o aproximación de arcotangentes

## 🧑‍💻 Requisitos

El programa funciona con librerías nativas de Python 3 (se prevee usar librerías extra en posteriores actualizaciones)

## 🔧 Cómo usar <a name = "tests"></a>

El programa solicitará al usuario la cantidad de decimales de pi a calcular.


```
Numero de decimales:
```

Si todos los pasos se efectuan bien, el resultado exitoso se denota como:

```
Resultado guardado en pi.txt
```

<strong>Nota: </strong>el programa está pensado para correr en un Codespaces dentro de este repositorio original, si quieres obtener la salida en un fork, deberás cambiar la ruta del objeto file. 
```
f = open("/workspaces/matecosas/TUREPOSITORIO/calcs/pi[instante].txt", "w")
```

Para cualquier otro caso simplemente reemplazar la ruta de escritura por la deseada por el usuario (seguramente se acabe determinando por consola, stay tuned)
## ⛏️ Programado en <a name = "built_using"></a>

- Python 3

## ✍️ Autores <a name = "authors"></a>

- [@pvtoari](https://github.com/pvtoari) - Implementación y codificación de la salida y entrada de datos.
- [@gaticoMaster](https://www.notienegithub.sad) - Sugirió y desarrolló el principio matemático para conseguir decimales
