import os
import subprocess

from render_sdk import Workflows

app = Workflows()


@app.task(timeout_seconds=900)
def migrate() -> dict:
    """Run Spring Boot startup in non-web mode so Flyway applies DB migrations."""
    env = os.environ.copy()
    command = [
        "java",
        "-jar",
        "app.jar",
        "--spring.main.web-application-type=none",
        "--spring.flyway.enabled=true",
    ]

    completed = subprocess.run(command, check=True, env=env)
    return {"ok": True, "returncode": completed.returncode}


if __name__ == "__main__":
    app.start()
