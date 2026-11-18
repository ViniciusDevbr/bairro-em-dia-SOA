#!/usr/bin/env bash
set -euo pipefail
BASE="${BASE_URL:-http://localhost:8080}"
echo "Health:"
curl -fsS "$BASE/api/health"
echo
echo "Bairros:"
curl -fsS "$BASE/api/bairros"
echo
echo "Categorias:"
curl -fsS "$BASE/api/categorias"
echo
echo "Swagger: $BASE/swagger-ui/index.html"
