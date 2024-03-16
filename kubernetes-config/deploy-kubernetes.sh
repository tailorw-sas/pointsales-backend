
kubectl apply -f namespace.yaml
kubectl apply -n medinec -f secrets/
kubectl apply -n medinec -f deployments/
kubectl apply -n medinec -f services/
