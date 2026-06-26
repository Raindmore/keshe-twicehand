import os, re, shutil

base = 'D:/twice-market-master/二手交易平台前端/src'
views_old = os.path.join(base, 'views')
views_new = os.path.join(base, 'views')

# Read current router
with open(os.path.join(base, 'router/index.js'), 'r', encoding='utf-8') as f:
    router_content = f.read()

# Read all Vue files' content
vue_files = {}
for root, dirs, files in os.walk(views_old):
    for f in files:
        if f.endswith('.vue'):
            path = os.path.join(root, f)
            rel = os.path.relpath(path, views_old)
            with open(path, 'r', encoding='utf-8') as fp:
                vue_files[rel] = fp.read()

print(f'Read {len(vue_files)} Vue files')
for rel in sorted(vue_files.keys()):
    print(f'  {rel}')

# Plan: create api/ modules and reorganize views
# api/ module files based on existing request.js pattern
print('\nCreating api/ modules...')

# Read request.js content
with open(os.path.join(base, 'utils/request.js'), 'r', encoding='utf-8') as f:
    request_js = f.read()

# Create api/index.js - re-export request
api_dir = os.path.join(base, 'api')
os.makedirs(api_dir, exist_ok=True)

# Create module API files
api_files = {
    'admin.js': 'import request from "../utils/request"\n\nexport const adminLogin = (username, password) => request.get("/admin/login", { params: { Ausername: username, Apassword: password } })\n\nexport const getAdminPage = (pageNum, pageSize, username) => request.get("/admin/getAdminPage", { params: { pageNum, pageSize, Ausername: username } })\n\nexport const getAdminAllTotal = () => request.get("/admin/getAdminAllTotal")\n',
    'student.js': 'import request from "../utils/request"\n\nexport const studentLogin = (username, password) => request.get("/student/login", { params: { Susername: username, Spassword: password } })\n\n'
}

for name, content in api_files.items():
    with open(os.path.join(api_dir, name), 'w', encoding='utf-8') as f:
        f.write(content)
    print(f'  Created api/{name}')

print('\nFrontend restructuring preparation:')
print('  - api/ modules created')
print('  - views will be reorganized into subdirectories')
print('  - router imports will be updated')
print('\nTo complete, we need to:')
print('  1. Move view files to feature-based subdirectories')
print('  2. Update router/index.js import paths')
print('  3. Keep components/ organized')
print('\nNote: Current view files already exist. The router already references:\n')
for m in re.finditer(r"../views/([^'\"]+)", router_content):
    print(f'    ../views/{m.group(1)}')

print('\n=== Analysis Complete ===')
