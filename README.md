# Lab3V

Proyecto correspondiente al **Laboratorio 3** — aplicación en **Spring Boot (Java)** preparada para ejecución local con Maven, contenedorización con Docker, despliegue en **Kubernetes (Minikube)**, monitoreo con **Prometheus + Grafana** y despliegue continuo con **ArgoCD**.

**Repositorio:**  
https://github.com/RUTENCO/lab3v

---

## Índice

- [Requisitos](#requisitos)
- [Configuración de la base de datos local](#configuración-de-la-base-de-datos-local)
- [Ejecución local con Maven](#ejecución-local-con-maven)
- [Construcción y ejecución con Docker](#construcción-y-ejecución-con-docker)
- [Despliegue en Minikube (Kubernetes)](#despliegue-en-minikube-kubernetes)
- [Uso de ArgoCD](#uso-de-argocd)
- [Monitoreo: Prometheus y Grafana](#monitoreo-prometheus-y-grafana)
- [Pruebas de sobrecarga](#pruebas-de-sobrecarga)
- [Comandos útiles](#comandos-útiles)
- [Buenas prácticas](#buenas-prácticas)
- [Flujo general del proyecto](#flujo-general-del-proyecto)
- [Autor](#autor)

---

## Requisitos

- Java 17+
- Maven o Maven Wrapper (`mvnw`)
- PostgreSQL en local
- Docker
- Minikube
- kubectl
- Helm (opcional)
- ArgoCD
- Prometheus
- Grafana

---

## Configuración de la base de datos local

Debe existir una base de datos PostgreSQL previamente configurada.

**Parámetros ejemplo:**

- Base de datos: `lab3v_db`
- Usuario: `postgres`
- Contraseña: `postgres`
- Puerto: `5432`

**Ejemplo en `application.properties`:**

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lab3v_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

---

## Ejecución local con Maven

### Compilar el proyecto:

```bash
./mvnw clean package
```

### Ejecutar el JAR:

```bash
java -jar target/lab3v-0.0.1-SNAPSHOT.jar
```

### Acceso:

```bash
http://localhost:8080
```

---

## Construcción y ejecución con Docker

### Construir la imagen:

```bash
docker build -t lab3v:latest .
```

### Ejecutar contenedor:

```bash
docker run --rm -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://host.docker.internal:5432/lab3v_db" \
  -e SPRING_DATASOURCE_USERNAME="postgres" \
  -e SPRING_DATASOURCE_PASSWORD="postgres" \
  lab3v:latest
```

---

## Despliegue en Minikube (Kubernetes)

### Iniciar Minikube:

```bash
minikube start
```

### Cargar imagen:

```bash
minikube image load lab3v:latest
```

### Desplegar PostgreSQL:

```bash
kubectl apply -f postgres-configmap.yml
kubectl apply -f postgres-deployment.yml
```

### Desplegar la aplicación:

```bash
kubectl apply -f lab3v-deployment.yml
```

### Verificar:

```bash
kubectl get pods
kubectl get svc
```

### Acceso:

```bash
minikube service lab3v
```

---

## Uso de ArgoCD

### Aplicar manifiesto:

```bash
kubectl apply -f lab3v-argocd-application.yml -n argocd
```

### Port forward:

```bash
kubectl -n argocd port-forward svc/argocd-server 8080:443
```

---

## Monitoreo: Prometheus y Grafana

### Instalar con Helm:

```bash
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add grafana https://grafana.github.io/helm-charts
helm repo update

helm install prometheus prometheus-community/kube-prometheus-stack --namespace monitoring --create-namespace
helm install grafana grafana/grafana --namespace monitoring
```

### Acceso a Grafana:

```bash
minikube service grafana -n monitoring
```

---

## Pruebas de Sobrecarga

### Ejemplo con hey:

```bash
hey -z 1m -c 200 http://localhost:8080/api/test
```

### Ejemplo con k6:

Crear archivo `loadtest.js`:

```javascript
import http from "k6/http";
import { sleep } from "k6";

export default function () {
  http.get("http://localhost:8080/api/test");
  sleep(1);
}
```

Ejecutar:

```bash
k6 run --vus 100 --duration 2m loadtest.js
```

---

## Comandos útiles

```bash
# Ver todos los pods
kubectl get pods -A

# Ver todos los servicios
kubectl get svc -A

# Ver logs de un pod
kubectl logs -f <pod-name>

# Describir un pod
kubectl describe pod <pod-name>

# Métricas de nodos
kubectl top nodes

# Métricas de pods
kubectl top pods

# Port forward
kubectl port-forward deployment/lab3v 8080:8080
```

---

## Flujo general del proyecto

1. **Desarrollo** en Spring Boot
2. **Ejecución local** con Maven
3. **Dockerización**
4. **Despliegue** en Minikube
5. **Automatización** con ArgoCD
6. **Monitoreo** con Prometheus
7. **Visualización** con Grafana
8. **Pruebas de sobrecarga**

---

## Autor

**RUTENCO**  
Repositorio: [github.com/RUTENCO/lab3v](https://github.com/RUTENCO/lab3v)
